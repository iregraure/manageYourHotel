package com.manageYourHotel.security.filter.jwt;

import java.sql.Date;

import javax.crypto.SecretKey;

import com.manageYourHotel.common.Constants;
import com.manageYourHotel.security.model.User;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JWTTokenProvider {

	private static SecretKey key;
	
	public static String generateToken(User user)
	{
		return Jwts.builder().setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.setSubject(user.getId().toString())
				.setId(user.getId().toString())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.claim("username", user.getUsername())
				.claim("roles", user.getRoles())
				.setExpiration(new Date(System.currentTimeMillis() + Constants.EXPIRATION_TIME))
				.signWith(getKey(), SignatureAlgorithm.HS512).compact();
	}
	
	public static SecretKey getKey()
	{
		if (key == null)
		{
			key = Keys.hmacShaKeyFor(Constants.SECRET.getBytes());
		}
		return key;
	}
	
	public static boolean validateToken (String token)
	{
		boolean valid = false;
		try
		{
			Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token);
			valid = true;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return valid;
	}
	
}
