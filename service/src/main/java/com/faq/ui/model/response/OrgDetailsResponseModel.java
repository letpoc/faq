package com.faq.ui.model.response;

import java.util.List;

public class OrgDetailsResponseModel {
	private long count;
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	private List<OrgListResponseModel> orgList;
	
	public List<OrgListResponseModel> getOrgList() {
		return orgList;
	}
	public void setOrgList(List<OrgListResponseModel> orgList) {
		this.orgList = orgList;
	}
	
	
	
}
