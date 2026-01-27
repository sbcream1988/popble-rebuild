package com.example.demo.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProvider {

	private final Key key;
	
	public TokenProvider(@Value("${jwt.secret}") String secretKey) {
		this.key = Keys.hmacShaKeyFor(secretKey.getBytes());				
	}
	
	//JWT 생성
	public String createToken(Long userId) {
		long now = System.currentTimeMillis();
		Date expiry = new Date(now + 1000 * 60 * 60 * 3);//3시간 유지
		
		return Jwts.builder()
				.setSubject(String.valueOf(userId))
				.setIssuedAt(new Date(now))
				.setExpiration(expiry)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}
	
	//JWT에서 UserId 꺼내오기
	public Long getUserId(String token) {
		return Long.parseLong(Jwts.parserBuilder().
									setSigningKey(key).
									build().
									parseClaimsJws(token).
									getBody().
									getSubject());
		
	}
	
	//토큰 유효성 검사
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}
