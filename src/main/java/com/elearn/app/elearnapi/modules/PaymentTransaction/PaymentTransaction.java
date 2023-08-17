package com.elearn.app.elearnapi.modules.PaymentTransaction;

import java.time.LocalTime;
import java.time.ZoneOffset;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PaymentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Double amount;

    private LocalTime transactionDate;

    public PaymentTransaction() {
        this.transactionDate = LocalTime.now(ZoneOffset.UTC);
    }

    public PaymentTransaction(
            Double amount) {
        this.amount = amount;
        this.transactionDate = LocalTime.now(ZoneOffset.UTC);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalTime transactionDate) {
        this.transactionDate = transactionDate;
    }

}
