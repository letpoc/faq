package com.faq.shared;

public enum SuccessMessage {
	
	VERIFIED_EMAIL_ADDRESS("Email verification has been completed successfully. You registration process has been completed successfully. Thank you."),
	CREATED_ACCOUNT("Your account has been created successfully. We have sent you an email verification link. Please check your registered email id and verifiy your email account to complete the registeration process."),
	UPDATED_PASSWORD("Password has been changed successfully."),
	APPROVED_ORG("Organization has been approved successfully");
	
	private String successMessage;
	SuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	
	public String getSuccessMessage() {
		return successMessage;
	}
	
	
}
