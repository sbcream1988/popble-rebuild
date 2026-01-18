package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

//예약 응답 DTO

@Getter
@Builder
public class ReservationResponseDTO {

	private Long id;
	
	//팝업 이름
	private String popupTitle;
	
	//예약자 이름
	private String reserverName;
	
	//예약자 연락처
	private String phoneNumber;
	
	//예약 인원
	private int count;
	
	//예약 생성 시간
	private LocalDateTime createdAt;
}
