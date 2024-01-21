package com.chatmrkhoi.chatmrkhoi.security;

import java.security.Key;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class generation_token {
	private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	
	public String generation_token_verify(String gmail) {
		Date currendate = new Date();
		Date exprireDate = new Date(currendate.getTime() +   (24 * 60 *60 * 1000) );
		String token = Jwts.builder()
				.setSubject(gmail)
				.signWith(key, SignatureAlgorithm.HS512)
				.setExpiration(exprireDate)
				.compact();
		return token;
	}
	
	public String generation_token_verify_refesh(String gmail) {
		Date currendate = new Date();
		Date exprireDate = new Date(currendate.getTime() + 3 * 24 * 60  * 60 * 1000 );
		String token = Jwts.builder()
				.setSubject(gmail)
				.signWith(key, SignatureAlgorithm.HS512)
				.setExpiration(exprireDate)
				.compact();
		return token;
	}
	
	public boolean checktokengmail(String token) {
		try {
			Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token);
			return true;
		} catch (Exception ex) {
			throw
			new AuthenticationCredentialsNotFoundException("JWT was exprired or incorrect",ex.fillInStackTrace());
		}
	}
	
	public String getUsernameFromJWTverify(String token){
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
	
	
}
