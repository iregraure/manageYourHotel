package com.manageYourHotel.security.filter;

import static com.manageYourHotel.security.filter.jwt.JWTTokenProvider.generateToken;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manageYourHotel.common.Constants;
import com.manageYourHotel.security.model.User;
import com.manageYourHotel.security.model.dto.UserDto;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter 
{
	
	private static AuthenticationManager authManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authManager)
	{
		this.authManager = authManager;
		setFilterProcessesUrl(Constants.LOGIN_URL);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		UserDto dto = null;
		try
		{
			dto = new ObjectMapper().readValue(request.getInputStream(), UserDto.class);
		}
		catch (IOException ioe)
		{
			throw new RuntimeException(ioe);
		}
		return authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		response.setHeader("access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Expose-Headers", "*");
		response.addHeader("Content-Type", "application/json");
		response.addHeader(Constants.HEADER_STRING, Constants.TOKEN_PREFIX + generateToken((User)authResult.getPrincipal()));
		response.getWriter().write(generateToken((User)authResult.getPrincipal()));
	}
	
}
