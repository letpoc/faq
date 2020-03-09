
/**
 * 
 */
package com.faq.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Keerti
 *
 * 
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNameNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6210273059495871561L;

	public UserNameNotFoundException() {
		
	}
	
	public UserNameNotFoundException(String message) {
		super(message);
	}

}
