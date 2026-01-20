package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

//슬롯 요청
@Setter
@Getter
public class PopupReservationSlotRequestDTO {

	//팝업 일자
	private LocalDate date;
	
	//팝업 시작 시간
	private LocalTime startTime;
	
	//팝업 종료 시간
	private LocalTime endTime;
	
	//해당 시간 최대 인원
	private int maxCount;
}
