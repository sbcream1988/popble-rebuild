package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MyPageResponseDTO {

	private Long userId;
	
	private String email;
	
	private String nickname;
	
	private String phoneNumber;
	
	private String address;
	
	private String profileImage;
	
}
