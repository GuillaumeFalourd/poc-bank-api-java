package com.example.bankpoc.models.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.bankpoc.util.DateHour;

@Entity
@Table(name = "cash_out")
public class CashOut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;
    private double value;
    private LocalDateTime date;

    public CashOut(){}

    public CashOut(Long accountId, double value) {
        this.accountId = accountId;
        this.value = value;
        this.date = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDate() {
        return DateHour.format(date);
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CashOut{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
