package com.example.bankpoc.service.interfaceServ;

import com.example.bankpoc.models.entity.Account;

public interface AccountService {

    Account findById(Long id);

    Account create(Account newAccount);

    Account update(Account account);

    void checkIfAccountExists(Account account);
}
