package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PopupReservationSlotRequestDTO;
import com.example.demo.dto.PopupReservationSlotResponseDTO;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.service.PopupReservationSlotService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SlotController {

	private final PopupReservationSlotService slotService;
	
	//슬롯생성
	@PostMapping("/popup/{popupId}/slots")
	public ResponseEntity<PopupReservationSlotResponseDTO> createSlot(@PathVariable(name = "popupId")Long popupId,
			@RequestBody PopupReservationSlotRequestDTO request, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
		return ResponseEntity.ok(slotService.createSlot(popupId, request, customUserDetails.getUserId()));
	}
	//슬롯 수정
	@PatchMapping("/slots/{slotId}")
	public ResponseEntity<PopupReservationSlotResponseDTO> editSlot(@PathVariable(name = "slotId") Long slotId, 
			@RequestBody PopupReservationSlotRequestDTO request, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
		return ResponseEntity.ok(slotService.editSlot(slotId, request, customUserDetails.getUserId()));
	}
	
	//슬롯 삭제
	@DeleteMapping("/slots/{slotId}")
	public ResponseEntity<String> deleteSlot(@PathVariable(name = "slotId") Long slotId, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
	 	slotService.deleteSlot(slotId, customUserDetails.getUserId());
	 	return ResponseEntity.ok("슬롯이 삭제되었습니다");
	}
	
	//슬롯 조회
	@GetMapping("/slots/{slotId}")
	public ResponseEntity<PopupReservationSlotResponseDTO> getSlot(@PathVariable(name = "slotId") Long slotId) {
		return ResponseEntity.ok(slotService.getSlot(slotId));
	}
	
	//팝업별 슬롯조회
	@GetMapping("/popup/{popupId}/slots")
	public ResponseEntity<List<PopupReservationSlotResponseDTO>> getSlotsByPopup(@PathVariable(name="popupId") Long popupId){
		return ResponseEntity.ok(slotService.getSlotsByPopup(popupId));
	}
	
	//팝업별 슬롯 날짜 조회
	@GetMapping("/popup/{popupId}/slots/date")
	public ResponseEntity<List<PopupReservationSlotResponseDTO>> getSlotsByPopupAndDate(@PathVariable(name = "popupId") Long popupId, @RequestParam(name = "date") LocalDate date){
		return ResponseEntity.ok(slotService.getSlotsByPopupAndDate(popupId, date));
	}
}
