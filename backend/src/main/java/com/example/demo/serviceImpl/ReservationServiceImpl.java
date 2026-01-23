package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Popup;
import com.example.demo.domain.PopupReservationSlot;
import com.example.demo.domain.Reservation;
import com.example.demo.dto.ReservationRequestDTO;
import com.example.demo.dto.ReservationResponseDTO;
import com.example.demo.repository.PopupRepository;
import com.example.demo.repository.PopupReservationSlotRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.ReviewBoardRepository;
import com.example.demo.service.ReservationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

	
	private final ReservationRepository reservationRepository;
	private final PopupRepository popupRepository;
	private final PopupReservationSlotRepository slotRepository;
	
	//예약 생성
	@Override
	@Transactional
	public ReservationResponseDTO createReserve(ReservationRequestDTO request) {
		
		Popup popup = popupRepository.findById(request.getPopupId())
					.orElseThrow(()->new IllegalArgumentException("해당 팝업을 찾을 수 없습니다"));
		
		PopupReservationSlot slot =  slotRepository.findById(request.getPopupReservationSlotId())
								.orElseThrow(()->new IllegalArgumentException("해당 슬롯을 찾을 수 없습니다"));
		
		//슬롯 검증(해당 팝업에 속하는지부터 확인)
		if(!slot.getPopup().getId().equals(popup.getId())) {
			throw new IllegalStateException("해당 슬롯은 이 팝업의 슬롯이 아닙니다");
		}
		
		//예약 가능 여부 확인
		if(!slot.isAvailable(request.getCount())) {
			throw new IllegalStateException("해당 시간대 예약 가능 인원을 초과했습니다");
		}
		
		//슬롯 인원 추가
		slot.increaseCount(request.getCount());
		
		Reservation reservation = Reservation.builder()
								.popup(popup)
								.popupReservationSlot(slot)
								.reserverName(request.getReserverName())
								.phoneNumber(request.getPhoneNumber())
								.count(request.getCount())
								.build();
		
		reservationRepository.save(reservation);
		
		return ReservationResponseDTO.builder()
				.id(reservation.getId())
				.popupTitle(reservation.getPopup().getTitle())
				.reserverName(reservation.getReserverName())
				.phoneNumber(reservation.getPhoneNumber())
				.count(reservation.getCount())
				.build();
	}
	
	//예약 변경
	@Override
	@Transactional
	public ReservationResponseDTO editReserve(Long reserveId, ReservationRequestDTO request) {
		
		Reservation reservation = reservationRepository.findById(reserveId)
								.orElseThrow(()->  new IllegalArgumentException("해당 예약이 존재하지 않습니다"));
		
		reservation.setReserverName(request.getReserverName());
		reservation.setPhoneNumber(request.getPhoneNumber());
		reservation.setCount(request.getCount());
		
		//슬롯 변경 필요시
		if(request.getPopupReservationSlotId() != null) {
			PopupReservationSlot slot = slotRepository.findById(request.getPopupReservationSlotId())
									.orElseThrow(()->new IllegalArgumentException("해당 슬롯이 존재하지 않습니다"));

			reservation.setPopupReservationSlot(slot);
		}
			
		return ReservationResponseDTO.builder()
				.id(reservation.getId())
				.popupTitle(reservation.getPopup().getTitle())
				.reserverName(reservation.getReserverName())
				.phoneNumber(reservation.getPhoneNumber())
				.count(reservation.getCount())
				.build();
	}
	//예약 취소
	@Override
	@Transactional
	public void cancelReserve(Long reserveId) {
		Reservation reservation = reservationRepository.findById(reserveId)
								.orElseThrow(()->new IllegalArgumentException("해당 예약이 존재하지 않습니다"));
		
		reservationRepository.delete(reservation);
	}
	
	//예약 조회
	@Override
	@Transactional(readOnly = true)
	public ReservationResponseDTO getReserve(Long reserveId) {
		Reservation reservation = reservationRepository.findById(reserveId)
								.orElseThrow(()->new IllegalArgumentException("해당 예약이 존재하지 않습니다"));
		
		return ReservationResponseDTO.builder()
				.id(reserveId)
				.popupTitle(reservation.getPopup().getTitle())
				.reserverName(reservation.getReserverName())
				.phoneNumber(reservation.getPhoneNumber())
				.count(reservation.getCount())
				.build();
	}
	
	//예약 리스트 조회(슬롯 기준)
	@Override
	@Transactional(readOnly = true)
	public List<ReservationResponseDTO> getReserveBySlot(Long slotId) {

		return reservationRepository.findByPopupReservationSlot_Id(slotId).stream()
				.map(reservation->ReservationResponseDTO.builder()
						.id(reservation.getId())
						.popupTitle(reservation.getPopup().getTitle())
						.reserverName(reservation.getReserverName())
						.phoneNumber(reservation.getPhoneNumber())
						.count(reservation.getCount())
						.build())
				.toList();
	}
}
