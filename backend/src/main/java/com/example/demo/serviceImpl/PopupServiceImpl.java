package com.example.demo.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Popup;
import com.example.demo.dto.PopupCardDTO;
import com.example.demo.dto.PopupCreateRequestDTO;
import com.example.demo.dto.PopupReservationSlotResponseDTO;
import com.example.demo.dto.PopupResponseDTO;
import com.example.demo.dto.PopupUpdateRequestDTO;
import com.example.demo.repository.PopupRepository;
import com.example.demo.service.PopupService;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PopupServiceImpl implements PopupService {

	private final PopupRepository popupRepository;
	
	//팝업 등록
	@Override
	@Transactional
	public PopupResponseDTO createPopup(PopupCreateRequestDTO popupCreateRequestDTO) {
		Popup popup = Popup.builder()
				.title(popupCreateRequestDTO.getTitle())
				.description(popupCreateRequestDTO.getDescription())
				.address(popupCreateRequestDTO.getAddress())
				.startDate(popupCreateRequestDTO.getStartDate())
				.endDate(popupCreateRequestDTO.getEndDate())
				.price(popupCreateRequestDTO.getPrice())
				.maxCapacity(popupCreateRequestDTO.getMaxCapacity())
				.createdAt(LocalDateTime.now())
				.build();
		
		Popup saved = popupRepository.save(popup);
		
		return PopupResponseDTO.builder()
				.id(saved.getId())
				.title(saved.getTitle())
				.description(saved.getDescription())
				.address(saved.getAddress())
				.startDate(saved.getStartDate())
				.endDate(saved.getEndDate())
				.price(saved.getPrice())
				.maxCapacity(saved.getMaxCapacity())
				.viewCount(saved.getViewCount())
				.createdAt(saved.getCreatedAt())
				.build();
	}

	//팝업 수정
	@Override
	@Transactional
	public PopupResponseDTO updatePopup(Long popupId, PopupUpdateRequestDTO popupUpdateRequestDTO) {
		Popup popup = popupRepository.findById(popupId)
				.orElseThrow(()-> new IllegalArgumentException("해당 팝업이 존재하지 않습니다"));
		
		popup.setTitle(popupUpdateRequestDTO.getTitle());
		popup.setDescription(popupUpdateRequestDTO.getDescription());
		popup.setAddress(popupUpdateRequestDTO.getAddress());
		popup.setStartDate(popupUpdateRequestDTO.getStartDate());
		popup.setEndDate(popupUpdateRequestDTO.getEndDate());
		popup.setPrice(popupUpdateRequestDTO.getPrice());
		popup.setMaxCapacity(popupUpdateRequestDTO.getMaxCapacity());
		
		return PopupResponseDTO.builder()
				.id(popup.getId())
				.title(popup.getTitle())
				.description(popup.getDescription())
				.address(popup.getAddress())
				.startDate(popup.getStartDate())
				.endDate(popup.getEndDate())
				.price(popup.getPrice())
				.maxCapacity(popup.getMaxCapacity())
				.build();
		
	}

	//팝업 삭제
	@Override
	@Transactional
	public void deletePopup(Long popupId) {
		Popup popup = popupRepository.findById(popupId)
				.orElseThrow(()->new IllegalArgumentException("해당 팝업이 존재하지 않습니다"));
		
		popupRepository.delete(popup);
		
	}

	//팝업 정보 조회
	@Override
	@Transactional(readOnly = true)
	public PopupResponseDTO getPopup(Long popupId) {
		
		Popup popup = popupRepository.findById(popupId)
				.orElseThrow(()->new IllegalArgumentException("해당 팝업이 존재하지 않습니다"));
		
		return PopupResponseDTO.builder()
				.id(popup.getId())
				.title(popup.getTitle())
				.description(popup.getDescription())
				.address(popup.getAddress())
				.startDate(popup.getStartDate())
				.endDate(popup.getEndDate())
				.price(popup.getPrice())
				.maxCapacity(popup.getMaxCapacity())
				.viewCount(popup.getViewCount())
				.createdAt(popup.getCreatedAt())
				
				.build();
	}

	//팝업 리스트
	@Override
	@Transactional(readOnly = true)
	public List<PopupCardDTO> getPopupCards() {
		
		return popupRepository.findAll().stream()
				.map(popup->PopupCardDTO.builder()
						.id(popup.getId())
						.title(popup.getTitle())
						.address(popup.getAddress())
						.startDate(popup.getStartDate())
						.endDate(popup.getEndDate())
						.price(popup.getPrice())
						.viewCount(popup.getViewCount())
						.build())
				.toList();
	}

	
}
