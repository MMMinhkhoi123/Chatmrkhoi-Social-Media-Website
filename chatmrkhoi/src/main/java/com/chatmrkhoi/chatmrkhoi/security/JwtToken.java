package com.chatmrkhoi.chatmrkhoi.security;

import java.security.Key;
import java.util.Date;

import com.chatmrkhoi.chatmrkhoi.design.Signleton.Signleton;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtToken {
	private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

	public String getToken(String gmail) {
		Date currendate = new Date();
		Date exprireDate = new Date(currendate.getTime() + Signleton.getInstance().getTimeTokenFresh());
		return Jwts.builder()
				.setSubject(gmail)
				.signWith(key, SignatureAlgorithm.HS512)
				.setExpiration(exprireDate)
				.compact();
	}

	public boolean checkToken(String token) {
		try {
			Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(token).getBody();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getUsernameFromToken(String token){
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
	
	
}
