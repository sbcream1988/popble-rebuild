package com.example.demo.mapper;

import com.example.demo.domain.Popup;
import com.example.demo.domain.PopupReservationSlot;
import com.example.demo.dto.PopupReservationSlotRequestDTO;
import com.example.demo.dto.PopupReservationSlotResponseDTO;

public class SlotMapper {

	public static PopupReservationSlot toEntity(PopupReservationSlotRequestDTO requestDTO, Popup popup) {
		return PopupReservationSlot.builder()
				.popup(popup)
				.date(requestDTO.getDate())
				.startTime(requestDTO.getStartTime())
				.endTime(requestDTO.getEndTime())
				.maxCount(requestDTO.getMaxCount())
				.currentCount(0)
				.build();
	}
	
	public static PopupReservationSlotResponseDTO fromEntity(PopupReservationSlot slot) {
		return PopupReservationSlotResponseDTO.builder()
				.id(slot.getId())
				.date(slot.getDate())
				.startTime(slot.getStartTime())
				.endTime(slot.getEndTime())
				.maxCount(slot.getMaxCount())
				.currentCount(slot.getCurrentCount())
				.build();
	}
}
