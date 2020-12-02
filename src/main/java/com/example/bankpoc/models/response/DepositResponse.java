package com.example.bankpoc.models.response;

public class DepositResponse {

    private double value;
    private String date;
    private String transferType;

    public DepositResponse(double value, String date, String transferType) {
        this.value = value;
        this.date = date;
        this.transferType = transferType;
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

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    @Override
    public String toString() {
        return "DepositResponse{" +
                ", value=" + value +
                ", date=" + date +
                ", transferType='" + transferType + '\'' +
                '}';
    }
}
