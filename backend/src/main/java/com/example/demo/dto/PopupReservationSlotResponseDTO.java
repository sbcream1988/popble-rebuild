package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

//슬롯 응답
@Getter
@Builder
public class PopupReservationSlotResponseDTO {

	private Long id;
	
	// 팝업 날짜
	private LocalDate date;
	
	// 시작, 종료일
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	// 최대 인원
	private int maxCount;
	
	//현재 예약 인원
	private int currentCount;
}
