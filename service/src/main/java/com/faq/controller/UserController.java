package com.faq.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faq.exceptions.ServiceException;
import com.faq.service.UserService;
import com.faq.shared.ErrorMessageList;
import com.faq.shared.SuccessMessageList;
import com.faq.shared.dto.UserDto;
import com.faq.ui.model.request.UserSignUpRequestModel;
import com.faq.ui.model.response.ErrorMessageResponseModel;
import com.faq.ui.model.response.SuccessMessageResponseModel;
import com.faq.ui.model.response.UserDetailsResponseModel;
import com.faq.ui.model.response.UserSignUpResponseModel;
import com.faq.validators.input.UserInputValidator;

@RestController
@RequestMapping(value = "users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(path = "/{userId}")
	public UserDetailsResponseModel getUser(@PathVariable String userId) {
		UserDetailsResponseModel userDetails = new UserDetailsResponseModel();
		UserDto userDto = userService.getUserByUserId(userId);
		if (userDto == null) {
			throw new ServiceException(ErrorMessageList.AUTHENTICATION_FAILED.getErrorMessage());
		}
		BeanUtils.copyProperties(userDto, userDetails);
		return userDetails;
	}

	@PostMapping
	public UserSignUpResponseModel createUser(@RequestBody UserSignUpRequestModel userModelRequest) throws Exception {
		UserInputValidator.SignUp(userModelRequest);
		if (!userService.isUserRecordEmpty()) {
			if (userService.getUserByEmail(userModelRequest.getEmail()) != null) {
				throw new ServiceException(ErrorMessageList.RECORD_ALREADY_EXISTS.getErrorMessage());
			}
		}
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userModelRequest, userDto);
		UserDto createUser = userService.createUser(userDto);
		UserSignUpResponseModel userSignUpModelResponse = new UserSignUpResponseModel();
		BeanUtils.copyProperties(createUser, userSignUpModelResponse);
		return userSignUpModelResponse;
	}

	@GetMapping("/verification/{userId}/{token}")
	public Object userVerification(@PathVariable("userId") String userId, @PathVariable("token") String token) {
		UserDto userDto = userService.getUserByUserId(userId);
		System.out.println(userDto.isEmailVerificationStatus());		
		if (userDto == null) throw new ServiceException(ErrorMessageList.NO_RECORD_FOUND.getErrorMessage());
		if (userDto.isEmailVerificationStatus()) throw new ServiceException(ErrorMessageList.EMAIL_VERIFICATION_ALREADY_COMPLETED.getErrorMessage());
		if(userDto.getEmailVerificationToken().equals(token)) {
			userService.setEmailVerificationStatus(userId);
			return new SuccessMessageResponseModel(new Date(), SuccessMessageList.VERIFIED_EMAIL_ADDRESS.getSuccessMessage());
		} else {
			return new ErrorMessageResponseModel(new Date(), ErrorMessageList.MISMATCH_PROVIDED_INFORMAT.getErrorMessage());
		}		
	}
	
	/*
	 * @GetMapping("/{userId}/{orgId}/{isActive}") public
	 * List<UserDetailsResponseModel> getUsers(@PathVariable String userId) {
	 * 
	 * }
	 */
	
	
	@GetMapping("/change-password/{email}/{oldPwd}/{newPwd}")
	public Object changePassword(
			@PathVariable("email") String email,
			@PathVariable("oldPwd") String oldPwd, 
			@PathVariable("newPwd") String newPwd) {
		
		boolean changePassword = userService.changePassword(email, oldPwd, newPwd);
		Object responseObject = changePassword ? new SuccessMessageResponseModel(new Date(), SuccessMessageList.UPDATED_PASSWORD.getSuccessMessage())
				: new ErrorMessageResponseModel(new Date(), ErrorMessageList.COULD_NOT_UPDATE_PASSWORD.getErrorMessage());
		return responseObject;
	}
	
	

}
