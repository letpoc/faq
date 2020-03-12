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


import com.faq.entity.OrgEntity;
import com.faq.entity.UserEntity;
import com.faq.exceptions.EmailVerificationException;

import com.faq.repository.OrgRepository;
import com.faq.repository.UserRepository;
import com.faq.service.UserService;
import com.faq.shared.Utils;
import com.faq.shared.dto.UserDto;




@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OrgRepository orgRepository;	
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user)  {		
		user.setEncryptPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setUserId(Utils.generateUserId(30));
		user.setRole(1);
		user.setEmailVerificationToken(Utils.generateRandomString(30));
		user.setOrgId(Utils.generateRandomString(50));
		
		// save details in users table		
		UserEntity userEntity = new UserEntity();		
		BeanUtils.copyProperties(user, userEntity);		
		UserEntity createUser = userRepository.save(userEntity);
		
		// save details in organization table
		OrgEntity orgEntity = new OrgEntity();		
		orgEntity.setName(user.getOrgName());
		BeanUtils.copyProperties(user, orgEntity);
		orgRepository.save(orgEntity);
		
		UserDto responseDto = new UserDto();	
		BeanUtils.copyProperties(createUser, responseDto);		
		return responseDto;		
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity == null) throw new UsernameNotFoundException(email);		
		if(!userEntity.isEmailVerificationStatus()) throw new EmailVerificationException("Please verify your email id");	
		System.out.println(userEntity.getEncryptPassword());
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
