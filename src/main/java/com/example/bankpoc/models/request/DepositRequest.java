package com.example.bankpoc.models.request;

import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DepositRequest {

    @NotBlank(message = "O campo deve ser informado")
    private Long accountId;

    @NotBlank(message = "O campo deve ser informado")
    @Pattern(regexp = "^(?:[1-9]\\d*|0)?(?:\\.\\d+)?$\n", message = "Valor informado inválido")
    private double value;

    @JsonCreator(mode = JsonCreator.Mode.DEFAULT)
    public DepositRequest(
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepositRequest that = (DepositRequest) o;
        return Double.compare(that.value, value) == 0 &&
                Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, value);
    }

    @Override
    public String toString() {
        return "DepositRequest{" +
                "accountId=" + accountId +
                ", value=" + value +
                '}';
    }
}

