package com.faq.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 4337580642037640598L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false, length=30)
	private String userId;
	
	@Column(nullable=false, length=50)
	private String firstName;
	
	@Column(nullable=false, length=50)
	private String lastName;
	
	@Column(nullable=false, length=100)
	private String email;
	
	@Column(nullable=false, length=100)
	private String encryptPassword;
	
	@Column(length=30)
	private String emailVerificationToken;
	
	@Column()
	private boolean emailVerificationStatus;
	
	@Column(nullable=false, length=100)
	private String orgName;
	
	@Column(nullable=false, length=100)
	private boolean orgActive;
	
	@Column(nullable=false, length=100)
	private boolean orgDisable;
	
	@Column(nullable=false, length=100)
	private int role;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public boolean isOrgActive() {
		return orgActive;
	}

	public void setOrgActive(boolean orgActive) {
		this.orgActive = orgActive;
	}

	public boolean isOrgDisable() {
		return orgDisable;
	}

	public void setOrgDisable(boolean orgDisable) {
		this.orgDisable = orgDisable;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}	
	
	
	
	
	
	

}
