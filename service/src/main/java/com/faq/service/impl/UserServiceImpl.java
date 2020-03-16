package com.faq.service.impl;

import java.util.ArrayList;
import java.util.Date;
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
import com.faq.exceptions.ServiceException;
import com.faq.repository.OrgRepository;
import com.faq.repository.UserRepository;
import com.faq.service.UserService;
import com.faq.shared.ErrorMessage;
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
	public boolean createUser(UserDto user)  {		
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
		orgEntity.setCreatedAt(new Date());
		BeanUtils.copyProperties(user, orgEntity);
		orgRepository.save(orgEntity);
		
			
		return true; 		
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity == null) throw new UsernameNotFoundException(email);	
		if(userEntity == null) throw new ServiceException(ErrorMessage.COULD_NOT_FOUND_EMAIL.getErrorMessage());
		if(!(userEntity.isEmailVerificationStatus())) throw new ServiceException(ErrorMessage.EMAIL_ADDRESS_NOT_VERIFIED.getErrorMessage());
		OrgEntity org = orgRepository.findByOrgId(userEntity.getOrgId());
		if(!org.isApprove()) throw new ServiceException(ErrorMessage.PENDING_APPROVAL_ORG.getErrorMessage());
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

	@Override
	public boolean changePassword(String userId, String oldPwd, String newPwd) {		
		UserEntity userEntity = userRepository.findByUserId(userId);
		if(userEntity == null) throw new ServiceException(ErrorMessage.COULD_NOT_FOUND_USER_ID.getErrorMessage());
		System.out.println(bCryptPasswordEncoder.matches(oldPwd, userEntity.getEncryptPassword()));
		if(bCryptPasswordEncoder.matches(oldPwd, userEntity.getEncryptPassword())) {
			userEntity.setEncryptPassword(bCryptPasswordEncoder.encode(newPwd));
			userRepository.save(userEntity);
			return true;
		} else {
			return false;
		}	
		
	}

	
	

	

	


}
