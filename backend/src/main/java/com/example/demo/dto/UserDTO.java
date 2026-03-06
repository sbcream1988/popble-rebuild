package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.domain.Gender;
import com.example.demo.domain.Provider;
import com.example.demo.domain.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 유저 DTO
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {

	private Long userId;
	
	@Email(message = "이메일 형식이 아닙니다")
	@NotEmpty(message = "이메일은 필수입니다")
	private String email;

	@NotEmpty(message = "비밀번호는 필수입니다")
	private String password;
	
	private Role role;
	
	private String nickname;
	
	private String profileImage;
	
	private LocalDate birthday;
	
	private Gender gender;
	
	private String phoneNumber;
	
	private String address;
	
	//OAuth
	
	private Provider provider;
	
	private String providerId;
	
	private LocalDate createAt;
}
