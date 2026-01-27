package com.example.demo.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Popup;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.dto.PopupCardDTO;
import com.example.demo.dto.PopupCreateRequestDTO;
import com.example.demo.dto.PopupResponseDTO;
import com.example.demo.dto.PopupUpdateRequestDTO;
import com.example.demo.mapper.PopupMapper;
import com.example.demo.repository.PopupRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PopupService;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PopupServiceImpl implements PopupService {

	private final PopupRepository popupRepository;
	private final UserRepository userRepository;
	
	//팝업 등록
	@Override
	@Transactional
	public PopupResponseDTO createPopup(PopupCreateRequestDTO popupCreateRequestDTO, Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다"));
		
		//권한 체크
		if(user.getRole() != Role.ROLE_COMPANY && user.getRole() != Role.ROLE_ADMIN) {
			throw new RuntimeException("팝업 등록 권한 없음");
		}
		
		Popup popup = PopupMapper.toEntity(popupCreateRequestDTO);
		
		popup.setOwner(user);
		
		popupRepository.save(popup);
		
		return PopupMapper.fromEntity(popup);
	}

	//팝업 수정
	@Override
	@Transactional
	public PopupResponseDTO updatePopup(Long popupId, PopupUpdateRequestDTO requestDTO, Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(()-> new IllegalArgumentException("해당 사용자가 존재하지 않습니다"));
		
		Popup popup = popupRepository.findById(popupId)
				.orElseThrow(()-> new IllegalArgumentException("해당 팝업이 존재하지 않습니다"));
		
		if(!popup.getOwner().getId().equals(userId) && user.getRole() != Role.ROLE_ADMIN) {
			throw new RuntimeException("팝업 수정 권한이 없습니다");
		}
		
		if(requestDTO.getTitle() != null) {
			popup.setTitle(requestDTO.getTitle());
		}
		if(requestDTO.getDescription() != null) {
			popup.setDescription(requestDTO.getDescription());
		}
		if(requestDTO.getAddress() != null) {
			popup.setAddress(requestDTO.getAddress());
		}
		if(requestDTO.getStartDate() != null) {
			popup.setStartDate(requestDTO.getStartDate());
		}
		if(requestDTO.getEndDate() != null) {
			popup.setEndDate(requestDTO.getEndDate());
		}
		if(requestDTO.getPrice() != 0){
			popup.setPrice(requestDTO.getPrice());
		}
		if(requestDTO.getMaxCapacity() != 0) {
			popup.setMaxCapacity(requestDTO.getMaxCapacity());
		}
		
		return PopupMapper.fromEntity(popup);
		
	}

	//팝업 삭제
	@Override
	@Transactional
	public void deletePopup(Long popupId,Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(()-> new IllegalArgumentException("해당 유저가 존재하지 않습니다"));
		
		Popup popup = popupRepository.findById(popupId)
				.orElseThrow(()->new IllegalArgumentException("해당 팝업이 존재하지 않습니다"));
		
		if(!popup.getOwner().getId().equals(userId) && user.getRole() != Role.ROLE_ADMIN) {
			throw new RuntimeException("삭제 권한이 없습니다");
		}
		
		popupRepository.delete(popup);
		
	}

	//팝업 정보 조회
	@Override
	@Transactional(readOnly = true)
	public PopupResponseDTO getPopup(Long popupId) {
		
		Popup popup = popupRepository.findById(popupId)
				.orElseThrow(()->new IllegalArgumentException("해당 팝업이 존재하지 않습니다"));
		
		return PopupMapper.fromEntity(popup);
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
