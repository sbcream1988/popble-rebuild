package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

//예약 요청 DTO
@Getter
@Setter
public class ReservationRequestDTO {

	//팝업스토어
	private Long popupId;
	
	//팝업 예약 슬롯
	private Long popupReservationSlotId;
	
	//예약자 이름
	private String reserverName;
	
	//예약자 전화번호
	private String phoneNumber;
	
	//예약 인원
	private int count;
}
