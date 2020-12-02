package com.example.bankpoc.controllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.bankpoc.controller.OperationController;
import com.example.bankpoc.base.BankBaseTest;
import com.example.bankpoc.models.entity.Transfer;
import com.example.bankpoc.models.enums.TypeTransfer;
import com.example.bankpoc.models.request.CashoutRequest;
import com.example.bankpoc.models.request.DepositRequest;
import com.example.bankpoc.models.request.TransferRequest;
import com.example.bankpoc.models.response.CashoutResponse;
import com.example.bankpoc.models.response.DepositResponse;
import com.example.bankpoc.models.response.TransferResponse;
import com.example.bankpoc.service.implement.OperationServiceImpl;
import com.example.bankpoc.util.DateHour;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(OperationController.class)
public class OperationControllerTest extends BankBaseTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OperationServiceImpl operationService;

    private String value;
    private DepositRequest depositRequest;
    private DepositResponse depositResponse;
    private CashoutRequest cashoutRequest;
    private CashoutResponse cashoutResponse;
    private TransferRequest transferRequest;
    private TransferResponse transferResponse;
    private Transfer transfer;
    private List<Transfer> transfers;

    @Before
    public void setUp() {
        value = "200.00";
        depositRequest = new DepositRequest(1L, 200);
        depositResponse = new DepositResponse(200, DateHour.format(LocalDateTime.now()), TypeTransfer.DEPOSIT.name());
        cashoutRequest = new CashoutRequest(1L, 200);
        cashoutResponse = new CashoutResponse(200, DateHour.format(LocalDateTime.now()), TypeTransfer.CASHOUT.name());
        transferRequest = new TransferRequest(1L, 2l, 200);
        transferResponse = new TransferResponse(2L, 1L,200, DateHour.format(LocalDateTime.now()),TypeTransfer.TRANSFER);
        transfer = new Transfer(transferRequest);
        transfers = new ArrayList<>();
        transfers.add(transfer);
    }

    @Test
    public void balanceTest() throws Exception {
        when(operationService.getBalance(anyLong())).thenReturn(value);
        mvc.perform(get("/operation/balance/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void depositTest() throws Exception {
        when(operationService.deposit(any(DepositRequest.class))).thenReturn(depositResponse);
        ObjectMapper mapper = new ObjectMapper();
        mvc.perform(post("/operation/deposit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(depositRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void cashoutTest() throws Exception {
        when(operationService.cashOut(any(CashoutRequest.class))).thenReturn(cashoutResponse);
        ObjectMapper mapper = new ObjectMapper();
        mvc.perform(post("/operation/cashout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(cashoutRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void transferTest() throws Exception {
        when(operationService.transfer(any(TransferRequest.class))).thenReturn(transferResponse);
        ObjectMapper mapper = new ObjectMapper();
        mvc.perform(post("/operation/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(cashoutRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void accountStatement() throws Exception {
        when(operationService.getTransfers(anyLong())).thenReturn(transfers);
        mvc.perform(get("/operation/accountStatement/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}