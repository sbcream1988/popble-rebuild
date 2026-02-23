package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.PopupCardDTO;
import com.example.demo.dto.PopupCreateRequestDTO;
import com.example.demo.dto.PopupResponseDTO;
import com.example.demo.dto.PopupUpdateRequestDTO;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.service.PopupService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/popup")
@RequiredArgsConstructor
public class PopupController {

	private final PopupService popupService;

	// 팝업 등록
	@PostMapping("/create")
	@PreAuthorize("hasRole('ROLE_COMPANY') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<PopupResponseDTO> create(@RequestPart("data") PopupCreateRequestDTO requestDTO,
			@RequestPart(value = "images", required = false) List<MultipartFile> images,
			@AuthenticationPrincipal CustomUserDetails custUserDetails) {
		return ResponseEntity.ok(popupService.createPopup(requestDTO, custUserDetails.getUserId(), images));
	}

	// 팝업 수정
	@PatchMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_COMPANY') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<PopupResponseDTO> edit(@PathVariable(name = "id") Long popupId,
			@RequestBody PopupUpdateRequestDTO requestDTO,
			@AuthenticationPrincipal CustomUserDetails customUserDetails) {
		return ResponseEntity.ok(popupService.updatePopup(popupId, requestDTO, customUserDetails.getUserId()));
	}

	// 팝업 삭제
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_COMPANY') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable(name = "id") Long popupId,
			@AuthenticationPrincipal CustomUserDetails customUserDetails) {
		popupService.deletePopup(popupId, customUserDetails.getUserId());
		return ResponseEntity.ok("팝업이 삭제되었습니다");
	}

	// 팝업 조회
	@GetMapping("/{id}")
	public ResponseEntity<PopupResponseDTO> get(@PathVariable(name = "id") Long popupId) {
		return ResponseEntity.ok(popupService.getPopup(popupId));
	}

	// 팝업 카드 조회
	@GetMapping("/list")
	public ResponseEntity<List<PopupCardDTO>> getList() {
		return ResponseEntity.ok(popupService.getPopupCards());
	}
	
	// === COMPANY 영역 ===
	// 내가 등록한 팝업 조회
	@GetMapping("/my")
	@PreAuthorize("hasRole('ROLE_COMPANY') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<PopupCardDTO>> getMyPopups(@AuthenticationPrincipal CustomUserDetails customUserDetails){
		return ResponseEntity.ok(popupService.getMyPopups(customUserDetails.getUserId()));
	}
}
