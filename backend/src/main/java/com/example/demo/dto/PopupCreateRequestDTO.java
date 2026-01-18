package com.example.demo.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

//팝업 생성시 등록 전용 DTO
//이미지, 좌표, 카테고리 나중에
@Setter
@Getter
public class PopupCreateRequestDTO {

	//이름
	private String title;
	
	//내용
	private String description;
	
	//주소
	private String address;
	
	//시작, 종료일
	private LocalDate startDate;
	private LocalDate endDate;
	
	//가격
	private int price;
	
	//최대 수용인원
	private int maxCapacity;
}
