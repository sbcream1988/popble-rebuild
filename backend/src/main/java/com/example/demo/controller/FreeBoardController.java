package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.FreeBoard;
import com.example.demo.dto.FreeBoardRequestDTO;
import com.example.demo.dto.FreeBoardResponseDTO;
import com.example.demo.service.FreeBoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/board/free")
@RequiredArgsConstructor
public class FreeBoardController {

	private final FreeBoardService freeBoardService;
	
	// 목록 가져오기
	@GetMapping("/list")
	public ResponseEntity<List<FreeBoardResponseDTO>> list(){
		return ResponseEntity.ok(freeBoardService.getList());
	}
	
	// 글 가져오기
	@GetMapping("/{id}")
	public ResponseEntity<FreeBoardResponseDTO> get(@PathVariable(name = "id") Long id){
		return ResponseEntity.ok(freeBoardService.get(id));
	}
	
	// 글 등록
	@PostMapping
	public ResponseEntity<FreeBoardResponseDTO> create(@RequestBody FreeBoardRequestDTO requestDTO){
		return ResponseEntity.ok(freeBoardService.create(requestDTO));
	}
	
	// 글 수정
	@PatchMapping("/{id}")
	public ResponseEntity<FreeBoardResponseDTO> update(@PathVariable(name = "id") Long id, @RequestBody FreeBoardRequestDTO requestDTO){
		return ResponseEntity.ok(freeBoardService.update(id, requestDTO));
	}
	
	// 글 삭제
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable(name = "id") Long id){
		freeBoardService.delete(id);
		return ResponseEntity.ok("삭제 완료");
	}
}
