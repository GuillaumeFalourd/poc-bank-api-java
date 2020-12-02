package com.example.bankpoc.models.entity;

import com.example.bankpoc.models.enums.TypeTransfer;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {

    private String transactionType;
    private double value;
    private String date;
    private Long accountIdDesposit;
    private Long accountIdRecipient;

    public Transaction(Long accountIdDesposit, String date, double value, TypeTransfer type) {
        this.accountIdDesposit = accountIdDesposit;
        this.date = date;
        this.value = value;
        this.transactionType = type.name();
    }

    public Transaction(TypeTransfer type, Long accountIdRecipient, String date, double value) {
        this.accountIdRecipient = accountIdRecipient;
        this.date = date;
        this.value = value;
        this.transactionType = type.name();
    }

    public Transaction(String date, double value, TypeTransfer type) {
        this.accountIdDesposit = accountIdDesposit;
        this.date = date;
        this.value = value;
        this.transactionType = type.name();
    }

    public Long getAccountIdDesposit() {
        return accountIdDesposit;
    }

    public void setAccountIdDesposit(Long accountIdDesposit) {
        this.accountIdDesposit = accountIdDesposit;
    }

    public Long getAccountIdRecipient() {
        return accountIdRecipient;
    }

    public void setAccountIdRecipient(Long accountIdRecipient) {
        this.accountIdRecipient = accountIdRecipient;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "accountIdDesposit=" + accountIdDesposit +
                ", accountIdRecipient=" + accountIdRecipient +
                ", date=" + date +
                ", value=" + value +
                '}';
    }
}
