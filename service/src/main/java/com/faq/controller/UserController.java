package com.faq.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faq.entity.UserEntity;
import com.faq.service.UserService;
import com.faq.shared.dto.UserDto;
import com.faq.ui.model.request.UserModelRequest;
import com.faq.ui.model.response.UserModelResponse;

@RestController
@RequestMapping(value="users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public String getUser() {
		return "user List"; 
	}
	
	@PostMapping
	public UserModelResponse createUser(@RequestBody UserModelRequest userModelRequest) {
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userModelRequest, userDto);
		
		 UserDto createUser = userService.createUser(userDto);
		
		 UserModelResponse userModelResponse = new UserModelResponse();
		 BeanUtils.copyProperties(createUser, userModelResponse);
		 
		 return userModelResponse;
	}

}
