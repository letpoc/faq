package com.faq.controller;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faq.exceptions.UserServiceException;
import com.faq.service.UserService;
import com.faq.shared.dto.UserDto;
import com.faq.ui.model.request.UserSignUpRequestModel;
import com.faq.ui.model.response.ErrorMessageResponseModel;
import com.faq.ui.model.response.SuccessMessageResponseModel;
import com.faq.ui.model.response.UserDetailsResponseModel;
import com.faq.ui.model.response.UserErrorMessages;
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
			throw new UserServiceException(UserErrorMessages.AUTHENTICATION_FAILED.getErrorMessage());
		}
		BeanUtils.copyProperties(userDto, userDetails);
		return userDetails;
	}

	@PostMapping
	public UserSignUpResponseModel createUser(@RequestBody UserSignUpRequestModel userModelRequest) throws Exception {
		UserInputValidator.SignUp(userModelRequest);
		if (!userService.isUserRecordEmpty()) {
			if (userService.getUserByEmail(userModelRequest.getEmail()) != null) {
				throw new UserServiceException(UserErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
			}
		}
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userModelRequest, userDto);
		UserDto createUser = userService.createUser(userDto);
		UserSignUpResponseModel userSignUpModelResponse = new UserSignUpResponseModel();
		BeanUtils.copyProperties(createUser, userSignUpModelResponse);
		return userSignUpModelResponse;
	}

	@SuppressWarnings("unused")
	@GetMapping("/verification/{userId}/{token}")
	public Object userVerification(@PathVariable("userId") String userId, @PathVariable("token") String token) {
		UserDto userDto = userService.getUserByUserId(userId);
		System.out.println(userDto.isEmailVerificationStatus());		
		if (userDto == null) throw new UserServiceException(UserErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		if (userDto.isEmailVerificationStatus()) throw new UserServiceException(UserErrorMessages.EMAIL_VERIFICATION_ALREADY_VERIFIED.getErrorMessage());
		if(userDto.getEmailVerificationToken().equals(token)) {
			userService.setEmailVerificationStatus(userId);
			return new SuccessMessageResponseModel("Email verification has been completed successfully");
		} else {
			return new ErrorMessageResponseModel(new Date(), "The provided information does not match");
		}		
	}

}
