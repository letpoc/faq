package com.faq.ui.model.response;

import java.util.List;

public class UserListResponseModel {
	private long count;
	private List<UserDetailsResponseModel> userDetailList;
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public List<UserDetailsResponseModel> getUserDetailList() {
		return userDetailList;
	}
	public void setUserDetailList(List<UserDetailsResponseModel> userDetailList) {
		this.userDetailList = userDetailList;
	}
	
}
