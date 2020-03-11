package com.faq.ui.model.response;

public enum UserErrorMessages {
	
	REQUIRED_FIRST_NAME_FIELD("Required firstName field"),
	REQUIRED_FIRST_NAME_VALUE("Required firstName value"),
	REQUIRED_FIRST_NAME_LENGTH("Required firstName length must be in between 3 to 20"),
	
	REQUIRED_LAST_NAME_FIELD("Required lastName field"),
	REQUIRED_LAST_NAME_VALUE("Required lastName value."),
	REQUIRED_LAST_NAME_LENGTH("Required lastName length must be in between 3 to 20"),
	
	REQUIRED_EMAIL_FIELD("Required email field"),
	REQUIRED_EMAIL_VALUE("Required email value"),
	REQUIRED_EMAIL_PATTERN("Email address is not valid"),
	
	REQUIRED_PASSWORD_FIELD("Required email field"),
	REQUIRED_PASSWORD_VALUE("Required email value"),
	REQUIRED_PASSWORD_PATTERN("Password must included atleast 1 uppercase character, 1 numeric value and 1 special character"),
	
	REQUIRED_ORG_NAME_FIELD("Required orgName field"),
	REQUIRED_ORG_NAME_VALUE("Required orgName value"),
	
	MISSING_REQUIRED_FIELD("Missing required field. Please check documentation for the required fields"),
	RECORD_ALREADY_EXISTS("This email ID is already exist"),
	INTERNAL_SERVER_ERROR("Internal server error"),
	NO_RECORD_FOUND("No record found"),
	AUTHENTICATION_FAILED("Authetication failed: User ID is not correct"),
	COULD_NOT_UPDATE_RECORD("Could not update record"),
	COULD_NOT_DELETE_RECORD("Could not delete record"),
	EMAIL_ADDRESS_NOT_VERIFIED("Email address not verified"),
	NO_RECORD_FOUND_SIGN_IN("Either email/password is incorrect or this account does not exist"),
	EMAIL_VERIFICATION_ALREADY_VERIFIED("Verification verification already verified");
	
	private String errorMessage;
	UserErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
