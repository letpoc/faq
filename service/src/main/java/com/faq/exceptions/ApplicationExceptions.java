package com.faq.exceptions;
 
import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.faq.ui.model.response.ErrorMessageResponseModel;

@ControllerAdvice
public class ApplicationExceptions {
	
	@ExceptionHandler(value = {UserServiceException.class})
	public final ResponseEntity<Object> handleUserServiceExcpetion(UserServiceException ex, WebRequest req) {	
		ErrorMessageResponseModel errorMessage = new ErrorMessageResponseModel(new Date(), ex.getMessage());		
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
}
