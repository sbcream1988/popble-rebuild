package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.dto.PopupReservationSlotRequestDTO;
import com.example.demo.dto.PopupReservationSlotResponseDTO;

public interface PopupReservationSlotService {

	//관리
	// 슬롯 등록
	PopupReservationSlotResponseDTO createSlot(Long popupId, PopupReservationSlotRequestDTO request, Long userId);
	// 슬롯 수정
	PopupReservationSlotResponseDTO editSlot(Long slotId, PopupReservationSlotRequestDTO request, Long userId);
	// 슬롯 삭제
	void deleteSlot(Long slotId,Long userId);
	
	//조회
	// 슬롯 조회
	PopupReservationSlotResponseDTO getSlot(Long slotId);
	// 팝업의 슬롯조회(리스트)
	List<PopupReservationSlotResponseDTO> getSlotsByPopup(Long PopupId);
	// 팝업의 슬롯조회(리스트) + 날짜
	List<PopupReservationSlotResponseDTO> getSlotsByPopupAndDate(Long popupId, LocalDate date);

}
