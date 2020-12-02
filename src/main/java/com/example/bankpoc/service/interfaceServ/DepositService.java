package com.example.bankpoc.service.interfaceServ;

import java.util.List;

import com.example.bankpoc.models.entity.Deposit;

public interface DepositService {

    Deposit create(Deposit newDeposit);

    List<Deposit> findCustomerDeposits(Long id);
}
