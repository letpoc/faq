package com.faq.service;



import org.springframework.security.core.userdetails.UserDetailsService;

import com.faq.shared.dto.UserDto;

public interface UserService extends UserDetailsService {
	
	
	
	UserDto createUser(UserDto userDto);
	UserDto getUser(String email);
}
