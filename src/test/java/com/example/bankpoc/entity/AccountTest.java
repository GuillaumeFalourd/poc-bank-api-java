package com.example.bankpoc.entity;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import org.junit.rules.ExpectedException;

import com.example.bankpoc.exception.BusinessException;
import com.example.bankpoc.models.entity.Account;

public class AccountTest {

    private Account account;
    private Account account1;
    private double valueDeposit;
    private ExpectedException thrown;

    @Before
    public void setUp() {
        account = new Account();
        account.setId(1L);
        account.setBalance(200);
        account.setCreatedAt(LocalDateTime.now());
        account1 = new Account(LocalDateTime.now(),100);
        thrown = ExpectedException.none();
    }

    @Test
    public void depositTest_PassingValueToDepositThereShouldBeNoReturn() {
        // given
        this.valueDeposit = 200;

        //when
        account.deposit(valueDeposit);
        //then
    }

    @Test
    public void depositTest_PassingNegativeValueToDepositShouldReturnException() {
        //given
        this.valueDeposit = 300;
        //when
        try {
            account1.deposit(valueDeposit);
        }
        catch (BusinessException exception) {
            //then
            assertEquals("Valor invalido para deposito", exception.getMessage());
        }
    }
}
