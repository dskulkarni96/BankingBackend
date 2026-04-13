package com.bank.app.controller;

import com.bank.app.dto.*;
import com.bank.app.service.BankingService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api")
@Tag(name = "Bank APIs", description = "Operations related to banking transactions")
public class BankController {

    @Autowired
    private BankingService bankingService;

    @Operation(
        summary = "Transfer money between accounts",
        description = "Transfers amount from one account to another with idempotency support"
    )
    @PostMapping("/transfer")
    public ResponseEntity<com.bank.app.dto.ApiResponse<TransferResponse>> transfer(
            @Valid @RequestBody TransferRequest request) {

        TransferResponse serviceResponse = bankingService.transfer(
                request.getFromAccount(),
                request.getToAccount(),
                request.getAmount(),
                request.getReferenceId()
        );

        com.bank.app.dto.ApiResponse<TransferResponse> response =
                new com.bank.app.dto.ApiResponse<>("SUCCESS", "Transfer completed", serviceResponse);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
        summary = "Get transaction history",
        description = "Fetch all transactions for a given account number"
    )
    @GetMapping("/transactions/{accountNumber}")
    public ResponseEntity<com.bank.app.dto.ApiResponse<Object>> getTransactions(
            @Parameter(description = "Account number to fetch transactions")
            @PathVariable String accountNumber) {

        Object transactions = bankingService.getTransactions(accountNumber);

        com.bank.app.dto.ApiResponse<Object> response =
                new com.bank.app.dto.ApiResponse<>("SUCCESS", "Transaction history fetched", transactions);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public String checkHealth() {
    	return "App Running";
    }
}