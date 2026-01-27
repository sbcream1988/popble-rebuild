package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.FreeBoard;
import com.example.demo.dto.FreeBoardRequestDTO;
import com.example.demo.dto.FreeBoardResponseDTO;

public interface FreeBoardService {
	//리스트 불러오기
	List<FreeBoardResponseDTO> getList(); 
	
	//작성
	FreeBoardResponseDTO create(FreeBoardRequestDTO requestDTO,Long userId);
	
	//게시글 가져오기
	FreeBoardResponseDTO get(Long id);
	
	//게시글 수정
	FreeBoardResponseDTO update(Long id, FreeBoardRequestDTO requestDTO, Long userId);
	
	//게시글 삭제
	void delete(Long id, Long userId);
}
