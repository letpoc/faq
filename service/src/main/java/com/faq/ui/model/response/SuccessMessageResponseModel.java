package com.faq.ui.model.response;

import java.util.Date;

public class SuccessMessageResponseModel {
	private int status = 200;
	private String statusCode = "OK";
	private String message;
	private Date timestamp = new Date();
	
	public SuccessMessageResponseModel(String message) {
		this.message = message;
	}
	
	public SuccessMessageResponseModel(String message, String userName) {
		this.message = "Hello "+userName+". "+message;
	}
	
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
