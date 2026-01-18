package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;import com.example.demo.domain.PopupReservationSlot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//팝업 상세조회 응답 DTO
@Getter
@Builder
public class PopupResponseDTO {

	private Long id;
	
	//이름
	private String title;
	
	//소개
	private String description;
	
	//주소
	private String address;
	
	//시작,종료일
	private LocalDate startDate;
	private LocalDate endDate;
	
	//가격
	private int price;
	
	//최대 수용인원
	private int maxCapacity;
	
	//조회수
	private int viewCount;
	
	//생성시간
	private LocalDateTime createdAt;
	
	//예약 가능시간
	private List<PopupReservationSlotResponseDTO> slots;
}
