package com.example.bankpoc.service.implement;

import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.example.bankpoc.exception.BusinessException;
import com.example.bankpoc.models.entity.Account;
import com.example.bankpoc.models.entity.CashOut;
import com.example.bankpoc.models.entity.Deposit;
import com.example.bankpoc.models.entity.Transfer;
import com.example.bankpoc.models.enums.TypeTransfer;
import com.example.bankpoc.models.request.CashoutRequest;
import com.example.bankpoc.models.request.DepositRequest;
import com.example.bankpoc.models.request.TransferRequest;
import com.example.bankpoc.models.response.CashoutResponse;
import com.example.bankpoc.models.response.DepositResponse;
import com.example.bankpoc.models.response.TransferResponse;
import com.example.bankpoc.models.response.TransfersResponse;
import com.example.bankpoc.service.interfaceServ.AccountService;
import com.example.bankpoc.service.interfaceServ.CashOutService;
import com.example.bankpoc.service.interfaceServ.ClientService;
import com.example.bankpoc.service.interfaceServ.DepositService;
import com.example.bankpoc.service.interfaceServ.OperationService;
import com.example.bankpoc.service.interfaceServ.TransferService;

@Component
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OperationServiceImpl implements OperationService {

    @Autowired
    ClientService clientService;

    @Autowired
    AccountService accountService;

    @Autowired
    TransferService transferService;

    @Autowired
    DepositService depositService;

    @Autowired
    CashOutService cashOutService;

    @Override
    public String getBalance(Long accountId) {
        clientService.checkIfClientNotExists(clientService.findByAccountId(accountId));
        Account account = accountService.findById(accountId);
        return "{ \"Balance\" : " + Double.valueOf(String.format(Locale.US, "%.2f", account.getBalance())) + " } ";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public DepositResponse deposit(DepositRequest depositRequest) {
        this.validValueTransaction(depositRequest.getValue());
        Account account = accountService.findById(depositRequest.getAccountId());
        accountService.checkIfAccountExists(account);
        account.deposit(depositRequest.getValue());
        Deposit deposit = depositService.create(new Deposit(depositRequest));
        accountService.update(account);
        return new DepositResponse(deposit.getValue(), deposit.getDate(),
                TypeTransfer.DEPOSIT.name());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CashoutResponse cashOut(CashoutRequest cashoutRequest) {
        Account account = accountService.findById(cashoutRequest.getAccountId());
        this.validValueTransaction(cashoutRequest.getValue());
        this.validValueCashout(account, cashoutRequest.getValue());
        account.cashOut(cashoutRequest.getValue());
        CashOut cashOut = cashOutService.create(cashoutRequest);
        accountService.update(account);
        return new CashoutResponse(cashOut.getValue(), cashOut.getDate(),
                TypeTransfer.CASHOUT.name());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public TransferResponse transfer(TransferRequest transferRequest) {
        validTransfer(transferRequest);
        this.validValueTransaction(transferRequest.getValue());
        Account accountDeposit = accountService.findById(transferRequest.getDepositAccountid());
        Account accountRecipient = accountService.findById(transferRequest.getRecipientAccountid());
        validValueTransfer(accountDeposit, transferRequest.getValue());
        accountDeposit.cashOut(transferRequest.getValue());
        accountRecipient.deposit(transferRequest.getValue());
        Transfer transfer = transferService.transfer(transferRequest);
        accountService.update(accountDeposit);
        accountService.update(accountRecipient);
        return new TransferResponse(transfer.getAccountIdDesposit(),
                transfer.getAccountIdRecipient(),
                transfer.getValue(),
                transfer.getDate(), TypeTransfer.TRANSFER);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Transfer> getTransfers(Long accountId) {
        Account account = accountService.findById(accountId);
        List<Transfer> transfers = transferService.getTransfers(accountId);
        List<Deposit> deposits = depositService.findCustomerDeposits(accountId);
        List<CashOut> cashOuts = cashOutService.findCustomeCashOuts(accountId);
        TransfersResponse transfersResponse = new TransfersResponse(accountId, transfers, deposits, cashOuts);
        return transfersResponse.getTransactions();
    }

    @Override
    public void validTransfer(TransferRequest transferRequest) {
        if (transferRequest.getDepositAccountid().equals(transferRequest.getRecipientAccountid()))
            throw new BusinessException("INVALID_TRANSFER", "Não é possivel fazer uma transação para a mesma conta");
    }

    private void validValueTransfer(Account accountDeposit, double value) {
        if(accountDeposit.getBalance()<value)
            throw new BusinessException("VALOR_INVALIDO_TRANSFERENCIA","Saldo Insuficiente", "value");
    }

    private void validValueCashout(Account account, double value) {
        if(account.getBalance()<value)
            throw new BusinessException("VALOR_INVALIDO_SAQUE","Valor Inválido para saque","value");
    }

    private void validValueTransaction(double value) {
        if(value < 1)
            throw new BusinessException("VALOR_INVALIDO","Valor Inválido para transações","value");
    }
}
