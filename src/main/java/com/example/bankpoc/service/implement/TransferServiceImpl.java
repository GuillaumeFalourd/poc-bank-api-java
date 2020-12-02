package com.example.bankpoc.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.bankpoc.models.entity.Transfer;
import com.example.bankpoc.models.request.TransferRequest;
import com.example.bankpoc.repository.TransferRepository;
import com.example.bankpoc.service.interfaceServ.TransferService;

@Component
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TransferServiceImpl implements TransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Override
    public Transfer transfer(TransferRequest transferRequest) {
        return transferRepository.save(new Transfer(transferRequest));
    }

    @Override
    public List<Transfer> getTransfers(Long idAccount) {
        List<Transfer> transfer = new ArrayList<>();
        transfer = transferRepository.findByTransactionWithId(idAccount);
        return transfer;
    }
}
