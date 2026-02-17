package com.example.demo.mapper;

import com.example.demo.domain.Image;
import com.example.demo.dto.ImageResponseDTO;

public class ImageMapper {

	public static ImageResponseDTO fromEntity(Image image) {
		return ImageResponseDTO.builder()
				.id(image.getId())
				.originName(image.getOriginName())
				.accessUrl(image.getAccessUrl())
				.isThumbnail(image.isThumbnail())
				.build();
	}
}
