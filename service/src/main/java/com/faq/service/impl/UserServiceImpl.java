package com.faq.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.faq.entity.UserEntity;
import com.faq.repository.UserRepository;
import com.faq.service.UserService;
import com.faq.shared.Utils;
import com.faq.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user) throws RuntimeException {
		
		UserEntity getUserByEmail = userRepository.findByEmail(user.getEmail());
		
		if(getUserByEmail != null) throw new RuntimeException("The user with this email id is already exists.");
		
		UserEntity userEntity = new UserEntity();	
		
		user.setEncryptPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		String publicUserId = utils.generateUserId(30);
		user.setUserId(publicUserId);
		user.setRoll(1);
		BeanUtils.copyProperties(user, userEntity);
		
		
		
		UserEntity createUser = userRepository.save(userEntity);
		
		UserDto responseDto = new UserDto();
		BeanUtils.copyProperties(createUser, responseDto);
		
		return responseDto;		
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	


}
