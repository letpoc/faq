package com.faq.exceptions;


public class ServiceException extends RuntimeException {	
	private static final long serialVersionUID = 5453791612742478856L;	
	public ServiceException() {}	
	public ServiceException(String message) {		
		super(message);			
	}	
}
