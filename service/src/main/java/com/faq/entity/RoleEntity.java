package com.faq.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class RoleEntity implements Serializable {

	private static final long serialVersionUID = -2518919505137174907L;
	
	@Id
	@GeneratedValue
	int id;
	
	@Column
	String role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public RoleEntity(int id, String role) {
		this.id = id;
		this.role = role;
	}
	
	
	
}
