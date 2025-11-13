package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.domain.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//게시판 DTO
@Getter
@Setter
@RequiredArgsConstructor
public class BoardDTO {
	private Long id;
	
	private String title;
	
	private String content;
	
	private User writer;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
}
