package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {
	
	@Email(message = "이메일 형식이 아닙니다")
	@NotEmpty(message = "이메일은 필수입니다")
	private String email;
	
	@NotEmpty(message = "이메일은 필수입니다")
	private String password;
}
