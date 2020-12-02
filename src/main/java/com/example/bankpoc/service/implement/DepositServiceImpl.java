package com.example.bankpoc.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.bankpoc.models.entity.Deposit;
import com.example.bankpoc.repository.DepositRepository;
import com.example.bankpoc.service.interfaceServ.DepositService;

@Component
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DepositServiceImpl implements DepositService {

    @Autowired
    DepositRepository depositRepository;

    @Override
    public Deposit create(Deposit newDeposit) {
        return depositRepository.save(newDeposit);
    }

    @Override
    public List<Deposit> findCustomerDeposits(Long id) {
        return depositRepository.findCustomerDeposits(id);
    }
}
