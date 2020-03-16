package com.faq.entity;

import java.io.Serializable;
import java.util.Date;

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
	private boolean disable;
	
	@Column(nullable=false)
	private Date createdAt;
	
	@Column(nullable=false, length=10)
	private boolean approve;

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
	

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isApprove() {
		return approve;
	}

	public void setApprove(boolean approve) {
		this.approve = approve;
	}
	
	

}
