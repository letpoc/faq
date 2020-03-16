package com.faq.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.faq.entity.UserEntity;
import com.faq.repository.OrgRepository;
import com.faq.repository.UserRepository;
import com.faq.service.OrgService;
import com.faq.service.UserService;
import com.faq.shared.EntityColumns;
import com.faq.shared.ErrorMessage;
import com.faq.shared.Role;
import com.faq.shared.SuccessMessage;
import com.faq.shared.dto.OrgDto;
import com.faq.shared.dto.UserDto;
import com.faq.ui.model.response.ErrorMessageResponseModel;
import com.faq.ui.model.response.OrgDetailsResponseModel;
import com.faq.ui.model.response.OrgListResponseModel;
import com.faq.ui.model.response.SuccessMessageResponseModel;

@RestController
@RequestMapping(value = "/org")
public class OrgController {

	@Autowired
	UserService userService;

	@Autowired
	OrgService orgService;

	@GetMapping("/approve/{orgId}")
	public Object approveOrg(@RequestHeader("userId") String userId, @PathVariable String orgId) {
		UserDto userDto = userService.getUserByColumnName(EntityColumns.USERS_BY_USER_ID, userId);
		if (userDto.getRole() == Role.SUPER_ADMIN) {
			orgService.approveOrg(orgId);
			return new SuccessMessageResponseModel(SuccessMessage.APPROVED_ORG.getSuccessMessage());
		}
		return new ErrorMessageResponseModel(ErrorMessage.AUTHORIZATION_FAILED.getErrorMessage());
	}

	@GetMapping("/disable/{orgId}")
	public Object disableOrg(@RequestHeader("userId") String userId, @PathVariable String orgId) {
		UserDto userDto = userService.getUserByColumnName(EntityColumns.USERS_BY_USER_ID, userId);
		if (userDto.getRole() == Role.SUPER_ADMIN) {
			orgService.disableOrg(orgId);
			return new SuccessMessageResponseModel(SuccessMessage.APPROVED_ORG.getSuccessMessage());
		}
		return new ErrorMessageResponseModel(ErrorMessage.AUTHORIZATION_FAILED.getErrorMessage());
	}

	@GetMapping("/enable/{orgId}")
	public Object enableOrg(@RequestHeader("userId") String userId, @PathVariable String orgId) {
		UserDto userDto = userService.getUserByColumnName(EntityColumns.USERS_BY_USER_ID, userId);
		if (userDto.getRole() == Role.SUPER_ADMIN) {
			orgService.disableOrg(orgId);
			return new SuccessMessageResponseModel(SuccessMessage.APPROVED_ORG.getSuccessMessage());
		}
		return new ErrorMessageResponseModel(ErrorMessage.AUTHORIZATION_FAILED.getErrorMessage());
	}

	@GetMapping
	public Object getOrgs(@RequestHeader("userId") String userId,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "25") int size) {
		UserDto userDto = userService.getUserByColumnName(EntityColumns.USERS_BY_USER_ID, userId);
		OrgDetailsResponseModel orgResponse = new OrgDetailsResponseModel();
		if (userDto.getRole() == Role.SUPER_ADMIN) {
			orgService.getOrgs(page, size);
			List<OrgDto> orgs = orgService.getOrgs(page, size);
			List<OrgListResponseModel> orgList = new ArrayList<>();
			for (OrgDto orgDto : orgs) {
				OrgListResponseModel org = new OrgListResponseModel();
				BeanUtils.copyProperties(orgDto, org);
				orgList.add(org);
			}
			orgResponse.setCount(orgService.getCount());
			orgResponse.setOrgList(orgList);
			return orgResponse;
		} else {
			return new ErrorMessageResponseModel(ErrorMessage.AUTHORIZATION_FAILED.getErrorMessage());
		}
	}

}
