package com.faq.ui.model.response;

import java.util.List;

public class UserDetailsResponseModel {
	private long count;
	private List<UserListResponseModel> userDetailList;
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public List<UserListResponseModel> getUserDetailList() {
		return userDetailList;
	}
	public void setUserDetailList(List<UserListResponseModel> userDetailList) {
		this.userDetailList = userDetailList;
	}
	
}
