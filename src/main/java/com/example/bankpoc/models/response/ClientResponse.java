package com.example.bankpoc.models.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientResponse {

    private String name;

    private String cpf;

    private Long accountId;

    @JsonCreator(mode = JsonCreator.Mode.DEFAULT)
    public ClientResponse(
            @JsonProperty("name") String name,
            @JsonProperty("cpf") String cpf,
            @JsonProperty("accountId") Long accountId) {

        this.name = name;
        this.cpf = cpf;
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
