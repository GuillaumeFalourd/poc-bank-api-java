package com.example.bankpoc.models.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TransferRequest {

    @NotBlank(message = "O campo deve ser informado")
    private Long depositAccountid;

    @NotBlank(message = "O campo deve ser informado")
    private Long recipientAccountid;

    @NotBlank(message = "O campo deve ser informado")
    @Min(1)
    private double value;

    @JsonCreator(mode = JsonCreator.Mode.DEFAULT)
    public TransferRequest(
            @JsonProperty("depositAccountid") Long depositAccountid,
            @JsonProperty("recipientAccountid") Long recipientAccountid,
            @JsonProperty("value") double value) {
        this.depositAccountid = depositAccountid;
        this.recipientAccountid = recipientAccountid;
        this.value = value;
    }

    public Long getDepositAccountid() {
        return depositAccountid;
    }

    public void setDepositAccountid(Long depositAccountid) {
        this.depositAccountid = depositAccountid;
    }

    public Long getRecipientAccountid() {
        return recipientAccountid;
    }

    public void setRecipientAccountid(Long recipientAccountid) {
        this.recipientAccountid = recipientAccountid;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TransferRequest{" +
                "depositAccountid=" + depositAccountid +
                ", recipientAccountid=" + recipientAccountid +
                ", value=" + value +
                '}';
    }
}
