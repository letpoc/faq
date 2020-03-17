package com.faq.shared.dto;

import java.io.Serializable;
import java.util.List;

public class UserDetailsDto implements Serializable {

	private static final long serialVersionUID = 7750980393479710823L;
	
	private long count;
	private List<UserDto> userListDto;
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public List<UserDto> getUserListDto() {
		return userListDto;
	}
	public void setUserListDto(List<UserDto> userListDto) {
		this.userListDto = userListDto;
	}	

}
