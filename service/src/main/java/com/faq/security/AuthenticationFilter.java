package com.faq.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.faq.SpringApplicationContext;
import com.faq.exceptions.ServiceException;
import com.faq.service.UserService;
import com.faq.shared.EntityColumns;
import com.faq.shared.ErrorMessage;
import com.faq.shared.dto.UserDto;
import com.faq.ui.model.request.LoginRequestModel;
import com.faq.ui.model.response.ErrorMessageResponseModel;
import com.faq.ui.model.response.UserListResponseModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;	

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {		
		try {					
			LoginRequestModel creds = new ObjectMapper().readValue(req.getInputStream(), LoginRequestModel.class);
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>());
			setDetails(req, token);
			return authenticationManager.authenticate(token);
		} catch (IOException e) {
			//UserServiceException ex = new UserServiceException("Bad Request");
			throw new ServiceException(req.getServerName());
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		String userName = ((User) auth.getPrincipal()).getUsername();
		String token = Jwts.builder().setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.getSecretToken()).compact();

		UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");
		// UserDto userDto = userService.getUserByEmail(userName);
		UserDto userDto = userService.getUserByColumnName(EntityColumns.USERS_BY_EMAIL, userName);
		UserListResponseModel userResponse = new UserListResponseModel();
		BeanUtils.copyProperties(userDto, userResponse);
		res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		res.addHeader("UserDetails", userResponse.toString());
		res.getWriter().write(userResponse.toString());
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException {
		SecurityContextHolder.clearContext();
		
		ErrorMessageResponseModel error = new ErrorMessageResponseModel(failed.getMessage());
		error.setStatus(403);
		error.setError("Access Denied");
		response.getWriter().write(error.toString());
	}

}
