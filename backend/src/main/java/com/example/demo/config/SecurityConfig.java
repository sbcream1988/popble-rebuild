package com.example.demo.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.security.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf(csrf->csrf.disable())
		.cors(cors->cors.configurationSource(corsConfigurationSource()))
		.authorizeHttpRequests(auth -> auth
				// 완전 공개
				.requestMatchers("/images/**","/api/auth/**", "/api/user/signup").permitAll()
				// 게시판 조회 공개
				.requestMatchers(HttpMethod.GET, "/api/board/free/**").permitAll()
				// 팝업, 슬롯 조회 공개
				.requestMatchers(HttpMethod.GET, "/api/popup/**", "/api/slots/**").permitAll()
				// 예약
				.requestMatchers("/api/reservation/**").authenticated()
				// 게시판 작성/수정은 USER이상만
				.requestMatchers("/api/board/**").hasAnyRole("USER","COMPANY","ADMIN")
				//=== Company / Admin만 사용 ===
				// 작성
				.requestMatchers(HttpMethod.POST, "/api/popup/**").hasAnyRole("COMPANY","ADMIN")
				// 수정
				.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("COMPANY", "ADMIN")
				// 삭제
				.requestMatchers(HttpMethod.DELETE, "/api/**").hasAnyRole("COMPANY","ADMIN")
				//나머지 로그인 필요
		.anyRequest().authenticated());
		
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	//비밀번호 암호화
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	//Cors설정
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("http://localhost:5173");
		configuration.addAllowedMethod("*");
		configuration.addAllowedHeader("*");
		configuration.setAllowCredentials(true);
		configuration.setExposedHeaders(List.of("Authorization"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
