package com.bank.app.exceptionhandler;

public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(String msg) {
		
		super(msg);
	}

}
