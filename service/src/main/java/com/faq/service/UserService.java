package com.faq.service;




import org.springframework.security.core.userdetails.UserDetailsService;

import com.faq.shared.dto.UserDto;

public interface UserService extends UserDetailsService {	
	boolean createUser(UserDto userDto);
	UserDto getUserByEmail(String email);
	UserDto getUserByUserId(String usedId);
	boolean isUserRecordEmpty();
	boolean setEmailVerificationStatus(String userId);
	boolean changePassword(String userId, String oldPwd, String newPwd);
}


