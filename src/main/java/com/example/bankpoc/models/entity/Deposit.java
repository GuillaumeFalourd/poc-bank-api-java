package com.example.bankpoc.models.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.bankpoc.models.enums.TypeTransfer;
import com.example.bankpoc.models.request.DepositRequest;
import com.example.bankpoc.util.DateHour;

@Entity
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long id_account;
    private double value;
    private LocalDateTime date;

    public Deposit(){}

    public Deposit(DepositRequest depositRequest) {
        this.id_account = depositRequest.getAccountId();
        this.value = depositRequest.getValue();
        this.date = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_account() {
        return id_account;
    }

    public void setId_account(Long id_account) {
        this.id_account = id_account;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDate() {
        return DateHour.format(date);
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "id=" + id +
                ", id_account=" + id_account +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
