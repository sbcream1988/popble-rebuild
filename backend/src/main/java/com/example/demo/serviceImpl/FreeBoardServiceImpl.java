package com.example.demo.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.FreeBoard;
import com.example.demo.domain.User;
import com.example.demo.dto.FreeBoardRequestDTO;
import com.example.demo.dto.FreeBoardResponseDTO;
import com.example.demo.repository.FreeBoardRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FreeBoardService;
import com.example.demo.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FreeBoardServiceImpl implements FreeBoardService{

	private final FreeBoardRepository freeBoardRepository;
	private final UserRepository userRepository;
	
	// 전체 목록 가져오기
	@Transactional(readOnly = true)
	public List<FreeBoardResponseDTO> getList(){
		return freeBoardRepository.findAll().stream()
				.map(board->new FreeBoardResponseDTO(board.getId(), board.getTitle(), board.getContent(), board.getWriter().getNickname(), board.getCreatedAt(), board.getUpdatedAt()))
				.toList();
	}
	
	// 글 작성
	@Transactional
	public FreeBoardResponseDTO create(FreeBoardRequestDTO requestDTO) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null || !(auth.getPrincipal() instanceof User user)) {
			throw new RuntimeException("로그인이 필요합니다");
		}
		
		
		FreeBoard board = FreeBoard.builder()
						.title(requestDTO.getTitle())
						.content(requestDTO.getContent())
						.createdAt(LocalDateTime.now())
						.writer(user)
						.build();
		
		freeBoardRepository.save(board);
		
		return FreeBoardResponseDTO.builder()
				.id(board.getId())
				.title(board.getTitle())
				.content(board.getContent())
				.nickname(board.getWriter().getNickname())
				.createdAt(board.getCreatedAt())
				.build();
	}
	
	@Transactional(readOnly = true)
	// 글 가져오기
	public FreeBoardResponseDTO get(Long id) {
		FreeBoard freeBoard =  freeBoardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
		
		return FreeBoardResponseDTO.builder()
				.id(freeBoard.getId())
				.title(freeBoard.getTitle())
				.content(freeBoard.getContent())
				.nickname(freeBoard.getWriter().getNickname())
				.createdAt(freeBoard.getCreatedAt())
				.updatedAt(freeBoard.getUpdatedAt())
				.build();
	}
	
	// 글 수정
	@Transactional
	public FreeBoardResponseDTO update(Long id, FreeBoardRequestDTO boardDTO) {
		FreeBoard freeBoard =  freeBoardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
		
		Long currentUserId = SecurityUtil.getCurrentUserId();
		
		if(!freeBoard.getWriter().getId().equals(currentUserId)) {
			throw new RuntimeException("수정 권한이 없습니다.");
		}
		
		if(boardDTO.getTitle() != null) {
			freeBoard.setTitle(boardDTO.getTitle());
		}
		if(boardDTO.getContent() != null) {
			freeBoard.setContent(boardDTO.getContent());
		}
		
		freeBoard.setTitle(boardDTO.getTitle());
		freeBoard.setContent(boardDTO.getContent());
		freeBoard.setUpdatedAt(LocalDateTime.now());;
		return FreeBoardResponseDTO.builder()
				.id(freeBoard.getId())
				.title(freeBoard.getTitle())
				.content(freeBoard.getContent())
				.updatedAt(freeBoard.getUpdatedAt())
				.build();
	}
	
	// 글 삭제
	@Transactional
	public void delete(Long id) {
		FreeBoard board = freeBoardRepository.findById(id)
						.orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
		
		Long currentUserId = SecurityUtil.getCurrentUserId();
		
		if(!board.getWriter().getId().equals(currentUserId)) {
			throw new RuntimeException("삭제 권한이 없습니다");
		}
		
		freeBoardRepository.deleteById(id);
	}
}
