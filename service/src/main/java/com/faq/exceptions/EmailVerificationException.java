package com.faq.exceptions;

import org.springframework.security.core.AuthenticationException;

public class EmailVerificationException extends AuthenticationException {
	private static final long serialVersionUID = 750337197280123246L;

	public EmailVerificationException(String message) {
		super(message);
	}
	
	

}
