package com.example.demo.mapper;

import com.example.demo.domain.Popup;
import com.example.demo.domain.PopupReservationSlot;
import com.example.demo.domain.Reservation;
import com.example.demo.dto.ReservationRequestDTO;
import com.example.demo.dto.ReservationResponseDTO;

public class ReservationMapper {

	public static Reservation toEntity(ReservationRequestDTO requestDTO, Popup popup, PopupReservationSlot slot) {
		return Reservation.builder()
				.popup(popup)
				.popupReservationSlot(slot)
				.reserverName(requestDTO.getReserverName())
				.phoneNumber(requestDTO.getPhoneNumber())
				.count(requestDTO.getCount())
				.build();
	}
	
	public static ReservationResponseDTO fromEntity(Reservation reservation) {
		return ReservationResponseDTO.builder()
				.id(reservation.getId())
				.popupTitle(reservation.getPopup().getTitle())
				.reserverName(reservation.getReserverName())
				.phoneNumber(reservation.getPhoneNumber())
				.count(reservation.getCount())
				.build();
	}
}
