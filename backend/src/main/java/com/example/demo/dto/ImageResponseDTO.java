package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageResponseDTO {

	private Long id;
	
	private String originName;
	private String accessUrl;
	private boolean isThumbnail;
}
