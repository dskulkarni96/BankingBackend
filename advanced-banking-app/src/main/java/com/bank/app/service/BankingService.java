package com.bank.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bank.app.dto.TransactionResponse;

import com.bank.app.dto.TransferResponse;
import com.bank.app.entity.Account;
import com.bank.app.entity.Transaction;
import com.bank.app.exceptionhandler.InsufficientBalanceException;
import com.bank.app.exceptionhandler.ResourceNotFoundException;
import com.bank.app.repository.AccountRepository;
import com.bank.app.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BankingService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(BankingService.class);

    @Transactional
    public  TransferResponse transfer(String fromAcc, String toAcc, Double amount, String refId) {

    	logger.info("Transfer started from {} to {} amount {}", fromAcc, toAcc, amount);
        logger.info("Transfer request received with refId {}", refId);

    	
    	 //IDEMPOTENCY CHECK
        Transaction existingTxn = transactionRepository.findByReferenceId(refId);

        if (existingTxn != null) {
            logger.info("Duplicate request detected for refId {}", refId);
            return new TransferResponse(existingTxn.getId()); // return existing txn
        }
    	
     try {
            // Step 2: CREATE txn FIRST (idempotency lock)
            Transaction txn = new Transaction();
            txn.setFromAccount(fromAcc);
            txn.setToAccount(toAcc);
            txn.setAmount(amount);
            txn.setTimestamp(LocalDateTime.now());
            txn.setReferenceId(refId);

            Transaction savedTxn = transactionRepository.save(txn);

            // Step 3: THEN update balances
            Account from = accountRepository.findByAccountNumber(fromAcc);
            Account to = accountRepository.findByAccountNumber(toAcc);

            if (from == null || to == null) {
                throw new ResourceNotFoundException("Invalid account");
            }

            if (from.getBalance() < amount) {
                throw new InsufficientBalanceException("Insufficient balance");
            }

            from.setBalance(from.getBalance() - amount);
            to.setBalance(to.getBalance() + amount);

            accountRepository.save(from);
            accountRepository.save(to);

             return new TransferResponse(savedTxn.getId());
     }catch (Exception e){
    	 
     }
	return null;

        
        
    }
    
    public List<TransactionResponse> getTransactions(String accNumber){
    	
    	List<Transaction> txns = transactionRepository.findByFromAccountOrToAccount(accNumber, accNumber);
    	 return txns.stream()
    		        .map(t -> new TransactionResponse(
    		            t.getFromAccount(),
    		            t.getToAccount(),
    		            t.getAmount(),
    		            t.getTimestamp()
    		        ))
    		        .toList();
    	
    }
}