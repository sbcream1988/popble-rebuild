package com.example.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NoticeBoardDTO extends BoardDTO{

	//상단 고정 여부
	private boolean pin; 
}
