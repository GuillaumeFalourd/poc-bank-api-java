package com.example.bankpoc.models.request;

import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientRequest {

    @Pattern(regexp = "^.{3,255}$", message = "O campo deve conter no mínimo 3 caracteres")
    @NotBlank(message = "O campo deve nome ser informado")
    private String name;

    @CPF(message = "O CPF informado está inválido")
    @NotBlank(message = "O campo deve cpf ser informado")
    private String cpf;

    @JsonCreator(mode = JsonCreator.Mode.DEFAULT)
    public ClientRequest(
            @JsonProperty("name") String name,
            @JsonProperty("cpf") String cpf) {
        this.name = name;
        this.cpf = cpf;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientRequest that = (ClientRequest) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(cpf, that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cpf);
    }

    @Override
    public String toString() {
        return "ClientRequest{" +
                "name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
