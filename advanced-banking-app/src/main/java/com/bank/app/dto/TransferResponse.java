package com.bank.app.dto;

public class TransferResponse {

    private Long transactionId;

    public TransferResponse(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getTransactionId() { return transactionId; }
}