package com.example.demo.mapper;

import com.example.demo.domain.Popup;
import com.example.demo.dto.PopupCardDTO;

public class PopupCardMapper {

	public static PopupCardDTO fromEntity(Popup popup, String thumbnailUrl) {
		
		return PopupCardDTO.builder()
				.id(popup.getId())
				.title(popup.getTitle())
				.address(popup.getAddress())
				.thumbnailUrl(thumbnailUrl)
				.startDate(popup.getStartDate())
				.endDate(popup.getEndDate())
				.price(popup.getPrice())
				.viewCount(popup.getViewCount())
				.build();
	}
}

