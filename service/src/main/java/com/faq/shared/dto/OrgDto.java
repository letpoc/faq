package com.faq.shared.dto;

import java.io.Serializable;
import java.util.Date;

public class OrgDto implements Serializable {

	private static final long serialVersionUID = 7405896139196812846L;
	
	private String orgId;
	private String name;
	private Date createdAt;
	private boolean approve;
	private boolean disable;
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
	
	public boolean isApprove() {
		return approve;
	}
	public void setApprove(boolean approve) {
		this.approve = approve;
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
	
	
	
	
	
}
