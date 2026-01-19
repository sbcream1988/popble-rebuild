package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PopupCardDTO;
import com.example.demo.dto.PopupCreateRequestDTO;
import com.example.demo.dto.PopupResponseDTO;
import com.example.demo.dto.PopupUpdateRequestDTO;
import com.example.demo.service.PopupService;
import com.example.demo.serviceImpl.PopupServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/popup")
@RequiredArgsConstructor
public class PopupController {

	private final PopupService popupService;

	
	//팝업 등록
	@PostMapping("/create")
	public PopupResponseDTO create(@RequestBody PopupCreateRequestDTO requestDTO) {
		return popupService.createPopup(requestDTO);
	}
	
	//팝업 수정
	@PatchMapping("/{id}")
	public PopupResponseDTO edit(@PathVariable(name = "id") Long popupId, @RequestBody PopupUpdateRequestDTO requestDTO) {
		return popupService.updatePopup(popupId, requestDTO);
	}
	
	//팝업 삭제
	@DeleteMapping("/{id}")
	public void delete(@PathVariable(name = "id") Long popupId) {
		popupService.deletePopup(popupId);
	}
	
	//팝업 조회
	@GetMapping("/{id}")
	public PopupResponseDTO get(@PathVariable(name = "id") Long popupId) {
		return popupService.getPopup(popupId);
	}
	
	//팝업 카드 조회
	@GetMapping("/list")
	public List<PopupCardDTO> getList(){
		return popupService.getPopupCards();
	}
}
