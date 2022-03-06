package com.example.lottery.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenProvider {
	@Value("${security.jwt.token.secret-key}") 
	private String secretKey;
	@Value("${security.jwt.token.expiration}")
	private long validityInMilliseconds;

	public String createToken(String username) {
		Claims claims = Jwts.claims().setSubject(username);

		// Build the Token
		Date now = new Date();
		return Jwts.builder().setClaims(claims).setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + validityInMilliseconds))
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}
}
