package com.example.bankpoc.models.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.example.bankpoc.exception.BusinessException;
import com.example.bankpoc.util.DateHour;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATE_CREATION")
    private LocalDateTime createdAt;

    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Account() {
    }

    public Account(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        this.balance = 0;
    }

    public Account(LocalDateTime createdAt, double balance) {
        this.createdAt = createdAt;
        this.balance = balance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return DateHour.format(createdAt);
    }

    public void deposit(double value) {
        this.balance += value;
    }

    public void cashOut(double value) {
        this.balance -= value;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", balance=" + balance +
                '}';
    }
}
