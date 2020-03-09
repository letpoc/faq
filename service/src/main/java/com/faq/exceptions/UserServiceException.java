package com.faq.exceptions;


public class UserServiceException extends RuntimeException {	
	private static final long serialVersionUID = 5453791612742478856L;	
	public UserServiceException() {}	
	public UserServiceException(String message) {		
		super(message);			
	}	
}
