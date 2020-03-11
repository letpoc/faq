package com.faq.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.faq.entity.UserEntity;
import com.faq.exceptions.EmailVerificationException;
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
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user)  {
		
		user.setEncryptPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setUserId(Utils.generateUserId(30));
		user.setRole(1);
		user.setEmailVerificationToken(Utils.generateRandomString(30));
		user.setOrgActive(false);
		user.setOrgDisable(false);
		user.setEmailVerificationStatus(false);
		UserEntity userEntity = new UserEntity();		
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
		if(!userEntity.isEmailVerificationStatus()) throw new EmailVerificationException("Please verify your email id");		
		return new User(userEntity.getEmail(), userEntity.getEncryptPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUserByEmail(String email) {				
		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity == null) return null;
		UserDto userDto	 = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public UserDto getUserByUserId(String userId) {		
		UserEntity userEntity = userRepository.findByUserId(userId);	
		if(userEntity == null) return null;
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);		
		return userDto;
	}

	@Override
	public boolean isUserRecordEmpty() {
		return userRepository.findAll().isEmpty();
	}

	@Override
	public boolean setEmailVerificationStatus(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		userEntity.setEmailVerificationStatus(true);
		userRepository.save(userEntity);
		return true;
	}

	
	

	

	


}
