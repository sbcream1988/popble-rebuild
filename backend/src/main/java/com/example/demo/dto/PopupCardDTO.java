package com.example.demo.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

//팝업 카드(간단 목록)
@Getter
@Builder
public class PopupCardDTO {

	private Long id;
	
	//이름
	private String title;
	
	//주소
	private String address;
	
	//시작, 종료일
	private LocalDate startDate;
	private LocalDate endDate;
	
	//가격(필요할수도 있음)
	private int price;
	
	//조회수
	private int viewCount;
	
	//썸네일
	private String thumbnailUrl;
}
