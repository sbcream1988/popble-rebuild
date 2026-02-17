package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.FreeBoard;
import com.example.demo.domain.User;
import com.example.demo.dto.FreeBoardRequestDTO;
import com.example.demo.dto.FreeBoardResponseDTO;
import com.example.demo.dto.PageRequestDTO;
import com.example.demo.dto.PageResponseDTO;
import com.example.demo.mapper.FreeBoardMapper;
import com.example.demo.repository.FreeBoardRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FreeBoardService;
import com.example.demo.service.ImageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FreeBoardServiceImpl implements FreeBoardService{

	private final FreeBoardRepository freeBoardRepository;
	private final UserRepository userRepository;
	private final ImageService imageService;
	
	// 전체 목록 가져오기
	@Transactional(readOnly = true)
	public PageResponseDTO<FreeBoardResponseDTO> getList(PageRequestDTO pageRequestDTO){
		
		Pageable pageable = PageRequest.of(pageRequestDTO.getPage() -1 , pageRequestDTO.getSize(), Sort.by("id").descending());
		
		Page<FreeBoard> result = freeBoardRepository.findAllWithWriter(pageable);
		
		List<FreeBoardResponseDTO> dtoList = result.getContent().stream()
				.map(FreeBoardMapper::fromEntity).toList();
		
		return new PageResponseDTO<FreeBoardResponseDTO>(dtoList,pageRequestDTO, result.getTotalElements());
		
		
	}
	
	// 글 작성
	@Transactional
	public FreeBoardResponseDTO create(FreeBoardRequestDTO requestDTO, Long userId, List<MultipartFile> files) {
		User user = userRepository.findById(userId)
				.orElseThrow(()->new IllegalArgumentException("사용자를 찾을 수 없습니다"));
				
		FreeBoard board = FreeBoardMapper.toEntity(requestDTO);
		
		board.setWriter(user);
		
		freeBoardRepository.save(board);
		
		// 이미지 업로드
		if(files != null) {
			for(MultipartFile file : files) {
				imageService.uploadImage(file, "FREEBOARD", board.getId(), false);
			}
		}
		
		return FreeBoardMapper.fromEntity(board);
	}
	
	@Transactional(readOnly = true)
	// 글 가져오기
	public FreeBoardResponseDTO get(Long id) {
		FreeBoard freeBoard =  freeBoardRepository.findById(id)
						.orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
		
		return FreeBoardMapper.fromEntity(freeBoard);
	}
	
	// 글 수정
	@Transactional
	public FreeBoardResponseDTO update(Long id, FreeBoardRequestDTO boardDTO, Long userId) {
		
		FreeBoard freeBoard =  freeBoardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

		if(!freeBoard.getWriter().getId().equals(userId)) {
			throw new RuntimeException("수정 권한이 없습니다.");
		}
		
		if(boardDTO.getTitle() != null) {
			freeBoard.setTitle(boardDTO.getTitle());
		}
		if(boardDTO.getContent() != null) {
			freeBoard.setContent(boardDTO.getContent());
		}
		
		return FreeBoardMapper.fromEntity(freeBoard);
	}
	
	// 글 삭제
	@Transactional
	public void delete(Long id, Long userId) {
		FreeBoard board = freeBoardRepository.findById(id)
						.orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

		if(!board.getWriter().getId().equals(userId)) {
			throw new RuntimeException("삭제 권한이 없습니다");
		}
		
		freeBoardRepository.delete(board);
	}
}
