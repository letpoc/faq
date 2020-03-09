package com.faq.ui.model.response;

public class UserDetailsResponseModel {
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private int roll;

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
	

	public int getRoll() {
		return roll;
	}

	public void setRoll(int roll) {
		this.roll = roll;
	}
	
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "{userId=\"" + userId + "\", firstName=\"" + firstName + "\", lastName=\"" + lastName + "\", email=\"" + email
				+ "\", roll=\"" + roll + "\"}";
	}
	
	
}
