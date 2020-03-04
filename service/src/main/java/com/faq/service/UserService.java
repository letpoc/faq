package com.faq.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.faq.shared.dto.UserDto;

public interface UserService extends UserDetailsService {
	
	
	
	UserDto createUser(UserDto userDto);

}
