package com.example.demo.controller;


import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.TokenProvider;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final TokenProvider tokenProvider;
	private final CustomUserDetailsService customUserDetailService;
	
	//로그인
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO, HttpServletResponse response){
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequestDTO.getEmail(), 
						loginRequestDTO.getPassword())
				);
		
		User user = (User)authentication.getPrincipal();
		
		String accessToken = tokenProvider.createToken(user.getId(), user.getRole());
		String refreshToken = tokenProvider.createRefreshToken(user.getId());
		
		Cookie cookie = new Cookie("refreshToken",refreshToken);
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		cookie.setPath("/");
		cookie.setMaxAge(60 * 60 * 24 * 7);
		
		response.addCookie(cookie);
		
		
		LoginResponseDTO loginResponseDTO = new LoginResponseDTO(
				accessToken,
				user.getId(),
				user.getEmail(),
				user.getNickname(),
				user.getRole());
		
		return ResponseEntity.ok(loginResponseDTO);
	}
	
	//로그아웃
	@PostMapping("/logout")
		public ResponseEntity<?> logout(HttpServletResponse response){
		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		cookie.setPath("/");
		cookie.setMaxAge(0); //즉시 삭제
		response.addCookie(cookie);
		
		return ResponseEntity.ok("로그아웃 성공");
	}
	
	//refreshToken
	@PostMapping("/auth/refresh")
	public ResponseEntity<?> refresh(HttpServletRequest request){
		String refreshToken = null;
		
		//쿠키에서 refresh토큰 꺼내기
		if(request.getCookies() != null) {
			for(Cookie c:request.getCookies()) {
				if(c.getName().equals("refreshToken")) {
					refreshToken = c.getValue();
				}
			}
		}
		
		//refresh토큰이 없거나 유효하지 않은 refresh토큰일 경우 차단
		if(refreshToken == null || !tokenProvider.validateRefreshToken(refreshToken)) {
			return ResponseEntity.status(401).body("Invalid RefreshToken");
		}
		
		//토큰에서 사용자 정보 꺼내기
		Long userId = tokenProvider.getUserId(refreshToken);
		Role role = tokenProvider.getRole(refreshToken);
		
		//새로운 Access토큰 발급
		String newAccessToken = tokenProvider.createToken(userId, role);
		
		//응답
		return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
	}
	
}
