package com.example.demo.exception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

	private final LocalDateTime timestamp = LocalDateTime.now();
	
	// HTTP 상태 코드 ( 400, 404, 500 )
	private final int status;
	
	// 에러이름
	private final String error;
	
	// 커스텀 코드
	private final String code;
	
	// 보여줄 메세지
	private final String message;
	
}
