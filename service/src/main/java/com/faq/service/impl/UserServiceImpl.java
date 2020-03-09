package com.faq.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.faq.entity.UserEntity;
import com.faq.exceptions.UserServiceException;
import com.faq.repository.UserRepository;
import com.faq.service.UserService;
import com.faq.shared.Utils;
import com.faq.shared.dto.UserDto;
import com.faq.ui.model.response.UserErrorMessages;



@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user)  {		
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
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity == null) throw new UsernameNotFoundException(email);		
		return new User(userEntity.getEmail(), userEntity.getEncryptPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUserByEmail(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		// if(userEntity == null) throw new UsernameNotFoundException(email);
		UserDto responseDto = new UserDto();
		BeanUtils.copyProperties(userEntity, responseDto);
		return responseDto;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		// if(userEntity == null) throw new UsernameNotFoundException(userId);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);		
		return userDto;
	}

	@Override
	public boolean isUserRecordEmpty() {
		return userRepository.findAll().isEmpty();
	}

	

	


}
