package com.manageYourHotel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.manageYourHotel.common.Constants;
import com.manageYourHotel.security.filter.JWTAuthenticationFilter;
import com.manageYourHotel.security.filter.JWTAuthorizationFilter;
import com.manageYourHotel.security.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
	
	@Autowired
	private JWTAuthorizationFilter jwtAuthoriztionFilter;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService service;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(passwordEncoder);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http.cors().and().csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.POST, Constants.LOGIN_URL).permitAll()
			.antMatchers(HttpMethod.POST, Constants.CLIENT_SIGNUP_URL).permitAll()
			.and().addFilter(new JWTAuthenticationFilter(authenticationManagerBean()))
			.addFilterBefore(jwtAuthoriztionFilter, BasicAuthenticationFilter.class).sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS); 	
	}

}
 