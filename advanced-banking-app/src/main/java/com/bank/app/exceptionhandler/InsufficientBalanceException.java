package com.bank.app.exceptionhandler;

public class InsufficientBalanceException extends RuntimeException{
	
	public InsufficientBalanceException(String msg) {
		super(msg);
	}

}
