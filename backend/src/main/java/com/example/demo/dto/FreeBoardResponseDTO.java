package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class FreeBoardResponseDTO {

	private Long id;
	
	private String title;
	
	private String content;
	
	private String nickname;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
}
