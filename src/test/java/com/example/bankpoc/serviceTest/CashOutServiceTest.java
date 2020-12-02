package com.example.bankpoc.serviceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.example.bankpoc.base.BankBaseTest;
import com.example.bankpoc.models.entity.CashOut;
import com.example.bankpoc.models.request.CashoutRequest;
import com.example.bankpoc.repository.CashOutRepository;
import com.example.bankpoc.service.implement.CashOutServiceImpl;

public class CashOutServiceTest extends BankBaseTest {

    @Mock
    private CashOutRepository cashOutRepository;

    @InjectMocks
    private CashOutServiceImpl cashOutService;

    private CashoutRequest cashoutRequest;
    private CashOut cashOut;

    private List<CashOut> cashOuts;

    @Before
    public void setUp() {
        cashOut = new CashOut();
        cashOut.setId(1L);
        cashOut.setAccountId(1L);
        cashOut.setDate(LocalDateTime.now());
        cashOut.setValue(200);
        cashoutRequest = new CashoutRequest((long) 1,200.0);
        cashOuts = new ArrayList<>();
        cashOuts.add(cashOut);
    }


    @Test
    public void createTest_WhenPassedValidDataReturnCashOut() {
        when(cashOutRepository.save(any(CashOut.class))).thenReturn(cashOut);
        CashOut cashOutResponse = cashOutService.create(cashoutRequest);
        assertEquals(cashOut.getId(),cashOutResponse.getId());
        assertEquals(cashOut.getAccountId(),cashOutResponse.getAccountId());
        assertEquals(cashOut.getDate(),cashOutResponse.getDate());
        assertEquals(cashOut.getValue(),cashOutResponse.getValue(),0);
        assertEquals(cashOut.toString(),cashOutResponse.toString());
    }

    @Test
    public void findCustomeCashOutsTest_WhenPassedValidDataReturnListCashOut() {
        when(cashOutRepository.getCashOutById_account(anyLong())).thenReturn(cashOuts);
        List<CashOut> cashOuts1 = cashOutService.findCustomeCashOuts(1L);
        assertEquals(1,cashOuts1.size());
    }
}
