package com.example.bankpoc.service.interfaceServ;

import java.util.List;

import com.example.bankpoc.models.entity.Transfer;
import com.example.bankpoc.models.request.CashoutRequest;
import com.example.bankpoc.models.request.DepositRequest;
import com.example.bankpoc.models.request.TransferRequest;
import com.example.bankpoc.models.response.CashoutResponse;
import com.example.bankpoc.models.response.DepositResponse;
import com.example.bankpoc.models.response.TransferResponse;

public interface OperationService {

    String getBalance(Long accountId);

    DepositResponse deposit(DepositRequest depositRequest);

    TransferResponse transfer(TransferRequest transferRequest);

    CashoutResponse cashOut(CashoutRequest cashoutRequest);

    List<Transfer> getTransfers(Long id);

    void validTransfer(TransferRequest transferRequest);
}
