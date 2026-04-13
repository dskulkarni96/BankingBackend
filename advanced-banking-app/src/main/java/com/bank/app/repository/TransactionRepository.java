package com.bank.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bank.app.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	List<Transaction> findByFromAccountOrToAccount(String from, String to);
	Transaction findByReferenceId(String referenceId);
}