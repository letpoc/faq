package com.faq.shared.dto;

import java.io.Serializable;

public class UserDto implements Serializable {

	private static final long serialVersionUID = -5772606005635995208L;
	
	private String userId;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String encryptPassword;
	private String emailVerificationToken;
	private boolean emailVerificationStatus;
	private int role;
	private String orgName;
	private String orgActive;
	private String orgDisable;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEncryptPassword() {
		return encryptPassword;
	}
	public void setEncryptPassword(String encryptPassword) {
		this.encryptPassword = encryptPassword;
	}
	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}
	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}
	public boolean isEmailVerificationStatus() {
		return emailVerificationStatus;
	}
	public void setEmailVerificationStatus(boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgActive() {
		return orgActive;
	}
	public void setOrgActive(String orgActive) {
		this.orgActive = orgActive;
	}
	public String getOrgDisable() {
		return orgDisable;
	}
	public void setOrgDisable(String orgDisable) {
		this.orgDisable = orgDisable;
	}
	public boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}
	
	
	

}
