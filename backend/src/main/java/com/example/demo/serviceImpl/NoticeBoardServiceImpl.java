package com.example.demo.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.NoticeBoard;
import com.example.demo.domain.User;
import com.example.demo.dto.NoticeBoardDTO;
import com.example.demo.repository.NoticeBoardRepository;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeBoardServiceImpl {

	private final UserRepository userRepository;
	private final NoticeBoardRepository noticeBoardRepository;
	
	// 목록 조회
	public List<NoticeBoard> getList(){
		return noticeBoardRepository.findAll();
	}
	
	// 글 생성
	public NoticeBoard create(NoticeBoardDTO noticeBoardDTO) {
		User user = (User) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		
		NoticeBoard noticeBoard = NoticeBoard.builder()
				.title(noticeBoardDTO.getTitle())
				.content(noticeBoardDTO.getContent())
				.writer(user)
				.createdAt(LocalDateTime.now())
				.pin(false)
				.build();
		
		return noticeBoardRepository.save(noticeBoard);
	}
	
	// 글 조회
	
	// 글 수정
	
	// 글 삭제
}
