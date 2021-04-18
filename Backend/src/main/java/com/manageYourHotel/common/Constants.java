package com.manageYourHotel.common;

public class Constants 
{
	public static final int MAX_ATTEMPTS = 3;
	public static final String SECRET = "Jwt_Secret_Manage_Your_Hotel_Final_Project_By_Irene_Granados_iregraure";
	public static final long EXPIRATION_TIME = 14_400_000;	// El token es válido durante 4 horas
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String LOGIN_URL = "/user/login";
	public static final String CLIENT_SIGNUP_URL = "/user/signUpClient";
}
