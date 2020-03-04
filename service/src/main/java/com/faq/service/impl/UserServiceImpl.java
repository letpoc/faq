package com.faq.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faq.entity.UserEntity;
import com.faq.repository.UserRepository;
import com.faq.service.UserService;
import com.faq.shared.dto.UserDto;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		UserEntity userEntity = new UserEntity();
		userDto.setEncryptPassword("password");
		userDto.setUserId("hello");
		BeanUtils.copyProperties(userDto, userEntity);
		
		UserEntity createUser = userRepository.save(userEntity);
		
		UserDto responseDto = new UserDto();
		BeanUtils.copyProperties(createUser, responseDto);
		
		return responseDto;
		
		
	}

}
