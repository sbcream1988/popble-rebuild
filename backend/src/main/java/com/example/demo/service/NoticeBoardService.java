package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.NoticeBoard;
import com.example.demo.dto.NoticeBoardDTO;

public interface NoticeBoardService {

	// 전체 목록 불러오기
	List<NoticeBoard> getList();
	
	// 글 작성
	NoticeBoard create(NoticeBoardDTO noticeBoardDTO);
	
	// 글 조회
	NoticeBoard get(Long id);
	
	// 글 수정
	NoticeBoard update(Long id, NoticeBoardDTO noticeBoardDTO);
	
	// 글 삭제
	void delete(Long id);
}
