package com.faq.service;




import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.faq.shared.dto.UserDetailsDto;
import com.faq.shared.dto.UserDto;

public interface UserService extends UserDetailsService {	
	boolean createUser(UserDto userDto);
	UserDetailsDto getUsers(String orgId, int page, int size);
	UserDto getUserByColumnName(String fieldName, String fieldValue);
	boolean isUserRecordEmpty();
	boolean setEmailVerificationStatus(String userId);
	boolean changePassword(String userId, String oldPwd, String newPwd);
}


