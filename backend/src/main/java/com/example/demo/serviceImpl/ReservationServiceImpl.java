package com.example.demo.serviceImpl;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Popup;
import com.example.demo.domain.PopupReservationSlot;
import com.example.demo.domain.Reservation;
import com.example.demo.domain.User;
import com.example.demo.dto.ReservationRequestDTO;
import com.example.demo.dto.ReservationResponseDTO;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.repository.PopupRepository;
import com.example.demo.repository.PopupReservationSlotRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ReservationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

	
	private final ReservationRepository reservationRepository;
	private final PopupRepository popupRepository;
	private final PopupReservationSlotRepository slotRepository;
	private final UserRepository userRepository;
	
	//예약 생성
	@Override
	@Transactional
	public ReservationResponseDTO createReserve(ReservationRequestDTO request, Long userId) {
		
		User user = null;
		if(userId !=null) {
			user = userRepository.findById(userId)
					.orElseThrow(()->new IllegalArgumentException("해당 유저를 찾을 수 없습니다"));
			request.setReserverName(user.getNickname());
		}
				
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
		
		Reservation reservation = ReservationMapper.toEntity(request, popup, slot);
		if(user!=null) {
			reservation.setReserver(user);
		}
		
		reservationRepository.save(reservation);
		
		return ReservationMapper.fromEntity(reservation);
	}
	
	//예약 변경
	@Override
	@Transactional
	public ReservationResponseDTO editReserve(Long reserveId, ReservationRequestDTO request, Long userId) {
		
		Reservation reservation = reservationRepository.findById(reserveId)
								.orElseThrow(()->  new IllegalArgumentException("해당 예약이 존재하지 않습니다"));
		
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다"));
		PopupReservationSlot oldSlot = reservation.getPopupReservationSlot();
		int oldCount = reservation.getCount();
		
		Long newSlotId = request.getPopupReservationSlotId();
		
		//슬롯 변경이 없는 경우
		if(newSlotId == null || newSlotId.equals(oldSlot.getId())) {
			int newCount = request.getCount();
			int diff = newCount - oldCount;
			
			if(diff > 0) {
				if(!oldSlot.isAvailable(diff)) {
					throw new IllegalStateException("해당시간 예약 가능 인원을 초과했습니다");
				}
				oldSlot.increaseCount(diff);
			}else if(diff < 0) {
				oldSlot.decreaseCount(-diff);
			}
		}
		
		//슬롯이 변경되는 경우
		else {
			PopupReservationSlot newSlot = slotRepository.findById(newSlotId)
					.orElseThrow(()->new IllegalArgumentException("해당 슬롯이 존재하지 않습니다"));
			
			oldSlot.decreaseCount(oldCount);
			
			if(!newSlot.isAvailable(request.getCount())) {
				throw new IllegalStateException("해당 시간대 에약 가능 인원을 초과했습니다");
			}
			
			newSlot.increaseCount(request.getCount());
			reservation.setPopupReservationSlot(newSlot);
		}

		reservation.setReserverName(request.getReserverName());
		reservation.setPhoneNumber(request.getPhoneNumber());
		reservation.setCount(request.getCount());
		

		return ReservationMapper.fromEntity(reservation);
	}
	
	//예약 취소
	@Override
	@Transactional
	public void cancelReserve(Long reserveId,Long userId) {
		Reservation reservation = reservationRepository.findById(reserveId)
								.orElseThrow(()->new IllegalArgumentException("해당 예약이 존재하지 않습니다"));
		
		PopupReservationSlot slot = reservation.getPopupReservationSlot();
		
		//슬롯인원 감소
		slot.decreaseCount(reservation.getCount());
		
		reservationRepository.delete(reservation);
	}
	
	//예약 조회
	@Override
	@Transactional(readOnly = true)
	public ReservationResponseDTO getReserve(Long reserveId,Long userId) {
		Reservation reservation = reservationRepository.findById(reserveId)
								.orElseThrow(()->new IllegalArgumentException("해당 예약이 존재하지 않습니다"));
		
		return ReservationMapper.fromEntity(reservation);
	}
	
	//예약 리스트 조회(슬롯 기준)
	@Override
	@Transactional(readOnly = true)
	public List<ReservationResponseDTO> getReserveBySlot(Long slotId) {

		return reservationRepository.findByPopupReservationSlot_Id(slotId).stream()
				.map(reservation-> ReservationMapper.fromEntity(reservation))
				.toList();
	}
}
