/**
 * 
 */
package com.faq.exception;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Keerti
 *
 * 
 */

@ControllerAdvice
@RestController
public class CustomisedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(UserNameNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleUserNameNotFoundException(UserNameNotFoundException ex,WebRequest request){
		
		ErrorResponse errorResponse = new ErrorResponse(new Date(),ex.getMessage(),request.getDescription(false));
		
		return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
		
	}
	
}
