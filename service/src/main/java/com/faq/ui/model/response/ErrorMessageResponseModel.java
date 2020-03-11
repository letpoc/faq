package com.faq.ui.model.response;

import java.util.Date;

public class ErrorMessageResponseModel {
	private long status = 400;
	private String statusCode = "error";
	private String error = "Bad Request";
	private String message;
	private Date timestamp;	

	public ErrorMessageResponseModel() {
	}
	
	public ErrorMessageResponseModel(Date timestamp, String message) {
		this.timestamp = timestamp;
		this.message = message;		
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
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

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String toString() {
		return "{\"status=\"" + status + "\", \"statusCode=\"" + statusCode + "\", error=\"" + error
				+ "\", message=\"" + message + "\", timestamp=\"" + timestamp + "\"}";
	}

	

	
	

}
