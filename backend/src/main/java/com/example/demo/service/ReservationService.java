package com.example.demo.service;

import java.security.Principal;
import java.util.List;

import com.example.demo.dto.ReservationRequestDTO;
import com.example.demo.dto.ReservationResponseDTO;

public interface ReservationService {

	//예약 생성
	ReservationResponseDTO createReserve(ReservationRequestDTO request, Long userId);
	
	//예약 변경
	ReservationResponseDTO editReserve(Long reserveId, ReservationRequestDTO request, Long userId);
	
	//예약 취소
	void cancelReserve(Long reserveId, Long userId);
	
	//예약 조회
	ReservationResponseDTO getReserve(Long reserveId,Long userId);
	
	//예약 리스트 조회(슬롯 기준)
	List<ReservationResponseDTO> getReserveBySlot(Long slotId);
}
