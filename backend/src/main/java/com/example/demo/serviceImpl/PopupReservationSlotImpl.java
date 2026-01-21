package com.example.demo.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Popup;
import com.example.demo.domain.PopupReservationSlot;
import com.example.demo.dto.PopupReservationSlotRequestDTO;
import com.example.demo.dto.PopupReservationSlotResponseDTO;
import com.example.demo.repository.PopupRepository;
import com.example.demo.repository.PopupReservationSlotRepository;
import com.example.demo.service.PopupReservationSlotService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PopupReservationSlotImpl implements PopupReservationSlotService {
	
	private final PopupReservationSlotRepository repository;
	private final PopupRepository popupRepository;
	
	//슬롯 생성
	@Override
	@Transactional
	public PopupReservationSlotResponseDTO createSlot(Long popupId, PopupReservationSlotRequestDTO request) {
		
		//1.팝업을 조회한다
		Popup popup = popupRepository.findById(popupId).
				orElseThrow(()->new IllegalArgumentException("해당 팝업이 존재하지 않습니다"));
		
		//2.중복 팝업슬롯이 있는지 확인한다.
		boolean exists = repository.existsByPopupIdAndDateAndStartTime(popupId, request.getDate(), request.getStartTime());
		
		if(exists) {
			throw new IllegalStateException("해당 시간에 슬롯이 이미 존재합니다");
		}
		
		//3.없으면 생성한다
		PopupReservationSlot slot = PopupReservationSlot.builder()
				.popup(popup)
				.date(request.getDate())
				.startTime(request.getStartTime())
				.endTime(request.getEndTime())
				.maxCount(request.getMaxCount())
				.currentCount(0)
				.build();
		
		repository.save(slot);
		
		return PopupReservationSlotResponseDTO.builder()
				.id(slot.getId())
				.date(slot.getDate())
				.startTime(slot.getStartTime())
				.endTime(slot.getEndTime())
				.maxCount(slot.getMaxCount())
				.currentCount(slot.getCurrentCount())
				.build();
	}
	
	//슬롯 수정
	@Override
	@Transactional
	public PopupReservationSlotResponseDTO editSlot(Long slotId, PopupReservationSlotRequestDTO request) {
		
		//해당 슬롯 조회
		PopupReservationSlot slot = repository.findById(slotId)
									.orElseThrow(()-> new IllegalArgumentException("해당 슬롯이 존재 하지 않습니다"));
		
		//슬롯 내용 수정
		slot.setDate(request.getDate());
		slot.setStartTime(request.getStartTime());
		slot.setEndTime(request.getEndTime());
		slot.setMaxCount(request.getMaxCount());
		
		//중복 슬롯 확인
		
		Long popupId = slot.getPopup().getId();
		boolean exists = repository.existsByPopupIdAndDateAndStartTimeAndIdNot(popupId, request.getDate(), request.getStartTime(), slotId);
		if(exists) {
			throw new IllegalStateException("해당 시간에 슬롯이 이미 존재합니다");
		}
		
		return PopupReservationSlotResponseDTO.builder()
				.id(slot.getId())
				.date(slot.getDate())
				.startTime(slot.getStartTime())
				.endTime(slot.getEndTime())
				.maxCount(slot.getMaxCount())
				.build();
	}
	
	//슬롯 삭제
	@Override
	@Transactional
	public void deleteSlot(Long slotId) {
		
		PopupReservationSlot slot = repository.findById(slotId)
									.orElseThrow(()->new IllegalArgumentException("해당 슬롯이 존재하지 않습니다"));
		repository.delete(slot);
	}
	
	//슬롯 조회
	@Override
	@Transactional(readOnly = true)
	public PopupReservationSlotResponseDTO getSlot(Long slotId) {
		
		PopupReservationSlot slot = repository.findById(slotId)
									.orElseThrow(() -> new IllegalArgumentException("해당 슬롯이 존재하지 않습니다"));
		
		return PopupReservationSlotResponseDTO.builder()
				.id(slot.getId())
				.date(slot.getDate())
				.startTime(slot.getStartTime())
				.endTime(slot.getEndTime())
				.maxCount(slot.getMaxCount())
				.build();
	}
	
	//팝업의 슬롯 리스트 조회
	@Override
	@Transactional(readOnly = true)
	public List<PopupReservationSlotResponseDTO> getSlotsByPopup(Long popupId){
		Popup popup = popupRepository.findById(popupId)
					.orElseThrow(()->new IllegalArgumentException("해당 팝업이 존재하지 않습니다"));
		
		return popup.getPopupReservationSlots().stream()
				.map(slot -> PopupReservationSlotResponseDTO.builder()
						.id(slot.getId())
						.date(slot.getDate())
						.startTime(slot.getStartTime())
						.endTime(slot.getEndTime())
						.maxCount(slot.getMaxCount())
						.currentCount(slot.getCurrentCount())
						.build()).toList();

	}
	
	//팝업의 슬롯 날짜 조회
	@Override
	@Transactional
	public List<PopupReservationSlotResponseDTO> getSlotsByPopupAndDate(Long popupId, LocalDate date){
		popupRepository.findById(popupId)
					.orElseThrow(()->new IllegalArgumentException("해당 팝업이 존재하지 않습니다"));
		
		return repository.findByPopupIdAndDateOrderByStartTime(popupId, date).stream()
				.map(slot -> PopupReservationSlotResponseDTO.builder()
						.id(slot.getId())
						.date(slot.getDate())
						.startTime(slot.getStartTime())
						.endTime(slot.getEndTime())
						.maxCount(slot.getMaxCount())
						.currentCount(slot.getCurrentCount()).build()).toList();
	}
}
