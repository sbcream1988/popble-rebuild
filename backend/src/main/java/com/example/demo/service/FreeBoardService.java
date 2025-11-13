package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.FreeBoard;
import com.example.demo.dto.BoardDTO;

public interface FreeBoardService {
	//리스트 불러오기
	List<FreeBoard> getList(); 
	
	//작성
	FreeBoard create(BoardDTO boardDTO);
	
	//게시글 가져오기
	FreeBoard get(Long id);
	
	//게시글 수정
	FreeBoard update(Long id, BoardDTO boardDTO);
	
	//게시글 삭제
	void delete(Long id);
}
