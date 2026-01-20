package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PopupReservationSlotRequestDTO;
import com.example.demo.dto.PopupReservationSlotResponseDTO;
import com.example.demo.service.PopupReservationSlotService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SlotController {

	private final PopupReservationSlotService slotService;
	
	//슬롯생성
	@PostMapping("/popup/{popupId}/slots")
	public PopupReservationSlotResponseDTO createSlot(@PathVariable(name = "popupId")Long popupId, @RequestBody PopupReservationSlotRequestDTO request) {
		return slotService.createSlot(popupId, request);
	}
	//슬롯 수정
	@PatchMapping("/slots/{slotId}")
	public PopupReservationSlotResponseDTO editSlot(@PathVariable(name = "slotId") Long slotId, @RequestBody PopupReservationSlotRequestDTO request) {
		return slotService.editSlot(slotId, request);
	}
	
	//슬롯 삭제
	@DeleteMapping("/slots/{slotId}")
	public void deleteSlot(@PathVariable(name = "slotId") Long slotId) {
		slotService.deleteSlot(slotId);
	}
	
	//슬롯 조회
	@GetMapping("/slots/{slotId}")
	public PopupReservationSlotResponseDTO getSlot(@PathVariable(name = "slotId") Long slotId) {
		return slotService.getSlot(slotId);
	}
	
	//팝업별 슬롯조회
	@GetMapping("/popup/{popupId}/slots")
	public List<PopupReservationSlotResponseDTO> getSlotsByPopup(@PathVariable("popupId") Long popupId){
		return slotService.getSlotsByPopup(popupId);
	}
}
