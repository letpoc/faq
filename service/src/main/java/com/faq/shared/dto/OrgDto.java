package com.faq.shared.dto;

import java.io.Serializable;

public class OrgDto implements Serializable {

	private static final long serialVersionUID = 7405896139196812846L;
	
	private String orgId;
	private String name;
	private boolean active;
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
