package com.example.demo.mapper;

import com.example.demo.domain.FreeBoard;
import com.example.demo.dto.FreeBoardRequestDTO;
import com.example.demo.dto.FreeBoardResponseDTO;

public class FreeBoardMapper {

	//Entity-> DTO
	public static FreeBoardResponseDTO fromEntity(FreeBoard freeBoard) {
		return FreeBoardResponseDTO.builder()
				.id(freeBoard.getId())
				.title(freeBoard.getTitle())
				.content(freeBoard.getContent())
				.nickname(freeBoard.getWriter().getNickname())
				.createdAt(freeBoard.getCreatedAt())
				.updatedAt(freeBoard.getUpdatedAt())
				.build();
	}
	
	//
	public static FreeBoard toEntity(FreeBoardRequestDTO requestDTO) {
		return FreeBoard.builder()
				.title(requestDTO.getTitle())
				.content(requestDTO.getContent())
				.build();
	}
}
