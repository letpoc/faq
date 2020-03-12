package com.faq.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="organizations")
public class OrgEntity implements Serializable {

	private static final long serialVersionUID = 3422744195973005649L;
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable=false, length=50)
	private String orgId;
	
	@Column(nullable=false, length=150)
	private String name;
	
	@Column(nullable=false)
	private boolean active;
	
	@Column(nullable=false)
	private boolean disable;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}
	
	

}
