package com.faq.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.faq.ui.model.response.UserDetailsResponseModel;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class AppAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setContentType("application/json;charset=UTF-8");
		
		List<Response> res = new ArrayList<>();
		res.add(new Response(403, "Forbidden", "Error"));
		
		GsonBuilder gson =  new GsonBuilder();
		JsonArray jarray = gson.create().toJsonTree(res).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("error", jarray);
		
		response.getWriter().write(jarray.toString());
		
		

	}
	
	class Response {
		public long status;
		public String error;
		public String message;
		public Response(long status, String error, String message) {
	
			this.status = status;
			this.error = error;
			this.message = message;
		}
		
		
		
	}

}




