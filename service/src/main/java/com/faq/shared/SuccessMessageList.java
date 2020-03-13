package com.faq.shared;

public enum SuccessMessageList {
	
	VERIFIED_EMAIL_ADDRESS("Email verification has been completed successfully"),
	UPDATED_PASSWORD("Password has been changed successfully.");
	
	private String successMessage;
	SuccessMessageList(String successMessage) {
		this.successMessage = successMessage;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	
}
