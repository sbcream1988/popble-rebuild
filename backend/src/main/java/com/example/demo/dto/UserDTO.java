package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.domain.Gender;
import com.example.demo.domain.Provider;
import com.example.demo.domain.Role;

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
	
	private String email;
	
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
