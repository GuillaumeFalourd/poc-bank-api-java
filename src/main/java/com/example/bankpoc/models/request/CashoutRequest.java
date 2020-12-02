package com.example.bankpoc.models.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CashoutRequest {

    @NotBlank(message = "O campo deve ser informado")
    private Long accountId;

    @NotBlank(message = "O campo deve ser informado")
    @Min(1)
    private double value;

    @JsonCreator(mode = JsonCreator.Mode.DEFAULT)
    public CashoutRequest(
            @JsonProperty("accountId") Long accountId,
            @JsonProperty("value") double value) {
        this.accountId = accountId;
        this.value = value;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CashoutRequest{" +
                "accountId=" + accountId +
                ", value=" + value +
                '}';
    }
}
