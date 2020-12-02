package com.example.bankpoc.service.interfaceServ;

import java.util.List;

import com.example.bankpoc.models.entity.Transfer;
import com.example.bankpoc.models.request.TransferRequest;

public interface TransferService {

    Transfer transfer(TransferRequest transferRequest);

    List<Transfer> getTransfers(Long id);
}
