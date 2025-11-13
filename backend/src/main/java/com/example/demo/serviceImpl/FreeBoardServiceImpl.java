package com.example.demo.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.FreeBoard;
import com.example.demo.domain.User;
import com.example.demo.dto.BoardDTO;
import com.example.demo.repository.FreeBoardRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FreeBoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FreeBoardServiceImpl implements FreeBoardService{

	private final FreeBoardRepository freeBoardRepository;
	private final UserRepository userRepository;
	
	// 전체 목록 가져오기
	public List<FreeBoard> getList(){
		return freeBoardRepository.findAll();
	}
	
	// 글 작성
	public FreeBoard create(BoardDTO boardDTO) {
		User user = userRepository.findById(boardDTO.getWriter().getId())
				.orElseThrow(()-> new RuntimeException("사용자를 찾을 수 없습니다"));
		
		FreeBoard freeBoard = FreeBoard.builder()
				.title(boardDTO.getTitle())
				.content(boardDTO.getContent())
				.writer(user)
				.createdAt(LocalDateTime.now())
				.build();
		
		return freeBoardRepository.save(freeBoard);
	}
	
	// 글 가져오기
	public FreeBoard get(Long id) {
		return freeBoardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
	}
	
	// 글 수정
	public FreeBoard update(Long id, BoardDTO boardDTO) {
		FreeBoard freeBoard =  freeBoardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
		freeBoard.setTitle(boardDTO.getTitle());
		freeBoard.setContent(boardDTO.getContent());
		freeBoard.setUpdatedAt(LocalDateTime.now());;
		return freeBoardRepository.save(freeBoard);
	}
	
	// 글 삭제
	public void delete(Long id) {
		freeBoardRepository.deleteById(id);
	}
}
