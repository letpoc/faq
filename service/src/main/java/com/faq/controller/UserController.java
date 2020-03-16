package com.faq.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.StringUtils;

import com.faq.exceptions.ServiceException;
import com.faq.repository.UserRepository;
import com.faq.service.UserService;
import com.faq.shared.EntityColumns;
import com.faq.shared.ErrorMessage;
import com.faq.shared.Role;
import com.faq.shared.SuccessMessage;
import com.faq.shared.dto.UserDto;
import com.faq.ui.model.request.ChangePasswordRequestModel;
import com.faq.ui.model.request.UserDetailsRequestModel;
import com.faq.ui.model.response.ErrorMessageResponseModel;
import com.faq.ui.model.response.SuccessMessageResponseModel;
import com.faq.ui.model.response.UserListResponseModel;
import com.faq.ui.model.response.UserDetailsResponseModel;
import com.faq.validators.input.UserInputValidator;

@RestController
@RequestMapping(value = "users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/{orgId}")
	public UserDetailsResponseModel getUsers(
			@RequestHeader("userId") String userId, 
			@PathVariable String orgId,
			@RequestParam(value = "page", defaultValue="0") int page,
			@RequestParam(value = "size", defaultValue="10") int size) {
		UserDto userDto = userService.getUserByColumnName(EntityColumns.USERS_BY_USER_ID, userId);
		if (userDto.getRole() == Role.SUPER_ADMIN || userDto.getRole() == Role.ADMIN) {
			List<UserDto> userDtoList = userService.getUsers(orgId, page, size);
		}
		return null;
	}

	@GetMapping(path = "/{userId}")
	public UserListResponseModel getUser(@PathVariable String userId) {
		UserListResponseModel userDetails = new UserListResponseModel();
		UserDto userDto = userService.getUserByColumnName(EntityColumns.USERS_BY_USER_ID, userId);
		if (userDto == null) {
			throw new ServiceException(ErrorMessage.AUTHENTICATION_FAILED.getErrorMessage());
		}
		BeanUtils.copyProperties(userDto, userDetails);
		return userDetails;
	}

	@PostMapping
	public SuccessMessageResponseModel signUpUser(@RequestBody UserDetailsRequestModel signUpModelRequest)
			throws Exception {
		UserInputValidator.SignUp(signUpModelRequest);
		signUpModelRequest.setFirstName(StringUtils.capitalize(signUpModelRequest.getFirstName()));
		signUpModelRequest.setLastName(StringUtils.capitalize(signUpModelRequest.getLastName()));
		if (!userService.isUserRecordEmpty()) {
			if (userService.getUserByColumnName(EntityColumns.USERS_BY_EMAIL, signUpModelRequest.getEmail()) != null) {
				throw new ServiceException(ErrorMessage.RECORD_ALREADY_EXISTS.getErrorMessage());
			}
		}
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(signUpModelRequest, userDto);
		userService.createUser(userDto);
		String userName = signUpModelRequest.getFirstName() + " " + signUpModelRequest.getLastName();
		return new SuccessMessageResponseModel(SuccessMessage.CREATED_ACCOUNT.getSuccessMessage(), userName);
	}

	@GetMapping("/verification/{userId}/{token}")
	public Object userVerification(@PathVariable("userId") String userId, @PathVariable("token") String token) {
		UserDto userDto = userService.getUserByColumnName(EntityColumns.USERS_BY_USER_ID, userId);
		System.out.println(userDto.isEmailVerificationStatus());
		if (userDto == null)
			throw new ServiceException(ErrorMessage.NO_RECORD_FOUND.getErrorMessage());
		if (userDto.isEmailVerificationStatus())
			throw new ServiceException(ErrorMessage.EMAIL_VERIFICATION_ALREADY_COMPLETED.getErrorMessage());
		if (userDto.getEmailVerificationToken().equals(token)) {
			userService.setEmailVerificationStatus(userId);
			return new SuccessMessageResponseModel(SuccessMessage.VERIFIED_EMAIL_ADDRESS.getSuccessMessage());
		} else {
			return new ErrorMessageResponseModel(ErrorMessage.MISMATCH_PROVIDED_INFORMAT.getErrorMessage());
		}
	}

	@PutMapping("/change-password/{userId}")
	public Object changePassword(@PathVariable String userId, @RequestBody ChangePasswordRequestModel pwd) {
		if (pwd.getOldPwd().equals(pwd.getNewPwd()))
			throw new ServiceException(ErrorMessage.MATCHED_OLD_NEW_PASSWORD.getErrorMessage());
		boolean isChangePassword = userService.changePassword(userId, pwd.getOldPwd(), pwd.getNewPwd());
		Object response = isChangePassword
				? new SuccessMessageResponseModel(SuccessMessage.UPDATED_PASSWORD.getSuccessMessage())
				: new ErrorMessageResponseModel(ErrorMessage.COULD_NOT_UPDATE_PASSWORD.getErrorMessage());
		return response;
	}

}
