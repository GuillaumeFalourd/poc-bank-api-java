package com.example.bankpoc.service.implement;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.bankpoc.exception.BusinessException;
import com.example.bankpoc.exception.NotFoundException;
import com.example.bankpoc.models.entity.Account;
import com.example.bankpoc.repository.AccountRepository;
import com.example.bankpoc.service.interfaceServ.AccountService;

@Component
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findById(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (!optionalAccount.isPresent())
            throw new NotFoundException("Conta Inexistente");
        return optionalAccount.get();
    }

    @Override
    public Account create(Account newAccount) {
        return accountRepository.save(newAccount);
    }

    @Override
    public Account update(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void checkIfAccountExists(Account account) {
        if (account == null)
            throw new NotFoundException("Conta Inexistente");
    }
}
