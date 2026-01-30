package com.example.demo.dto;

import com.example.demo.domain.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

	private String accessToken;
	private Long userId;
	private String email;
	private String nickname;
	private Role role;
}
