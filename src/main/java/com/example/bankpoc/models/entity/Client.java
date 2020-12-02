package com.example.bankpoc.models.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String cpf;


    @Column(name = "ID_ACCOUNT")
    private Long accountId;

    private String name;

    @Column(name = "DATE_CREATION")
    private LocalDateTime createdAt;

    public Client() {
    }

    public Client(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                ", accountId=" + accountId +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                Objects.equals(cpf, client.cpf) &&
                Objects.equals(accountId, client.accountId) &&
                Objects.equals(name, client.name) &&
                Objects.equals(createdAt, client.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, accountId, name, createdAt);
    }
}
