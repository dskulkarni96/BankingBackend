package com.bank.app.dto;

import java.time.LocalDateTime;

public class TransactionResponse {

    private String fromAccount;
    private String toAccount;
    private Double amount;
    private LocalDateTime timestamp;

    public TransactionResponse(String from, String to, Double amount, LocalDateTime time) {
        this.fromAccount = from;
        this.toAccount = to;
        this.amount = amount;
        this.timestamp = time;
    }

    public String getFromAccount() { return fromAccount; }
    public String getToAccount() { return toAccount; }
    public Double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
}