package com.example.demo.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

//팝업 수정시 DTO
@Getter
@Setter
public class PopupUpdateRequestDTO {

	//이름
	private String title;
	
	//내용
	private String description;
	
	//주소
	private String address;
	
	//시작일 종료일
	private LocalDate startDate;
	private LocalDate endDate;
	
	//가격
	private Integer price;
	
	//최대인원
	private Integer maxCapacity;
}
