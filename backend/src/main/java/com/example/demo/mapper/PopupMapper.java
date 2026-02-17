package com.example.demo.mapper;

import java.util.List;

import com.example.demo.domain.Popup;
import com.example.demo.dto.ImageResponseDTO;
import com.example.demo.dto.PopupCreateRequestDTO;
import com.example.demo.dto.PopupResponseDTO;

public class PopupMapper {

	public static PopupResponseDTO fromEntity(Popup popup, List<ImageResponseDTO> images) {
		return PopupResponseDTO.builder()
				.id(popup.getId())
				.title(popup.getTitle())
				.description(popup.getDescription())
				.address(popup.getAddress())
				.startDate(popup.getStartDate())
				.endDate(popup.getEndDate())
				.maxCapacity(popup.getMaxCapacity())
				.price(popup.getPrice())
				.createdAt(popup.getCreatedAt())
				.images(images)
				.build();
	}
	
	public static Popup toEntity(PopupCreateRequestDTO requestDTO) {
		return Popup.builder()
				.title(requestDTO.getTitle())
				.description(requestDTO.getDescription())
				.address(requestDTO.getAddress())
				.startDate(requestDTO.getStartDate())
				.endDate(requestDTO.getEndDate())
				.maxCapacity(requestDTO.getMaxCapacity())
				.price(requestDTO.getPrice())
				.build();
	}
	
}
