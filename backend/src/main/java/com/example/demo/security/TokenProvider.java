package com.example.demo.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.domain.Role;

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
	public String createToken(Long userId, Role role) {
		long now = System.currentTimeMillis();
		Date expiry = new Date(now + 1000 * 60 * 60 * 3);//3시간 유지
		
		return Jwts.builder()
				.setSubject(String.valueOf(userId))
				.claim("role", role.name())
				.claim("type","access")
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
	
	//Refresh토큰 생성
	public String createRefreshToken(Long userId) {
		long now = System.currentTimeMillis();
		//7일
		Date expiry = new Date(now + 1000 * 60 * 60 * 24 * 7);
		
		return Jwts.builder()
				.setSubject(String.valueOf(userId))
				.claim("type", "refresh")	//토큰 용도 구분
				.setIssuedAt(new Date(now))
				.setExpiration(expiry)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}
	
	//Refresh 토큰 유효성 검사
	public boolean validateRefreshToken(String token) {
		try {
			var claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
			return "refresh".equals(claims.get("type"));
		}catch(Exception e) {
			return false;
		}
	}
	
	public Role getRole(String token) {
		String role = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.get("role", String.class);
		
		return Role.valueOf(role);
	}
}
