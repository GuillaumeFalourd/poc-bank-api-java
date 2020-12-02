package com.example.bankpoc.service.interfaceServ;

import java.util.List;

import com.example.bankpoc.models.entity.CashOut;
import com.example.bankpoc.models.request.CashoutRequest;

public interface CashOutService {

    CashOut create(CashoutRequest cashoutRequest);

    List<CashOut> findCustomeCashOuts(Long id);
}
