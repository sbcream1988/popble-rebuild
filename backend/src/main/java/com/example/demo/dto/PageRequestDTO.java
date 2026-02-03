package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {

	private int page = 1; //기본값 1
	private int size = 10; //기본값 10 한페이지당 사이즈
	
	
}
