package com.example.bankpoc.models.response;

import com.example.bankpoc.models.enums.TypeTransfer;

public class TransferResponse {

    private String typeTransfer;
    private Long accountIdDesposit;
    private Long accountIdRecipient;
    private double value;
    private String date;

    public TransferResponse(Long accountIdDesposit, Long accountIdRecipient, double value, String date, TypeTransfer typeTransfer) {
        this.typeTransfer = typeTransfer.name();
        this.accountIdDesposit = accountIdDesposit;
        this.accountIdRecipient = accountIdRecipient;
        this.value = value;
        this.date = date;
    }

    public String getTypeTransfer() {
        return typeTransfer;
    }

    public void setTypeTransfer(String typeTransfer) {
        this.typeTransfer = typeTransfer;
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TransferResponse{" +
                "typeTransfer='" + typeTransfer + '\'' +
                ", accountIdDesposit=" + accountIdDesposit +
                ", accountIdRecipient=" + accountIdRecipient +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
