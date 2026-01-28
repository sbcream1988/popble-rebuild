package com.example.demo.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Popup;
import com.example.demo.domain.PopupReservationSlot;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.dto.PopupReservationSlotRequestDTO;
import com.example.demo.dto.PopupReservationSlotResponseDTO;
import com.example.demo.mapper.SlotMapper;
import com.example.demo.repository.PopupRepository;
import com.example.demo.repository.PopupReservationSlotRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PopupReservationSlotService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PopupReservationSlotImpl implements PopupReservationSlotService {

	private final PopupReservationSlotRepository repository;
	private final PopupRepository popupRepository;
	private final UserRepository userRepository;

	// 슬롯 생성
	@Override
	@Transactional
	public PopupReservationSlotResponseDTO createSlot(Long popupId, PopupReservationSlotRequestDTO request,
			Long userId) {

		// 1.팝업을 조회한다
		Popup popup = popupRepository.findById(popupId)
				.orElseThrow(() -> new IllegalArgumentException("해당 팝업이 존재하지 않습니다"));

		// 2.중복 팝업슬롯이 있는지 확인한다.
		boolean exists = repository.existsByPopupIdAndDateAndStartTimeLessThanAndEndTimeGreaterThan(popupId,
				request.getDate(), request.getEndTime(), request.getStartTime());

		if (exists) {
			throw new IllegalStateException("해당 시간에 슬롯이 이미 존재합니다");
		}

		// 3.없으면 생성한다
		PopupReservationSlot slot = SlotMapper.toEntity(request, popup);

		repository.save(slot);

		return SlotMapper.fromEntity(slot);
	}

	// 슬롯 수정
	@Override
	@Transactional
	public PopupReservationSlotResponseDTO editSlot(Long slotId, PopupReservationSlotRequestDTO request, Long userId) {
		
		// 해당 슬롯 조회
		PopupReservationSlot slot = repository.findById(slotId)
				.orElseThrow(() -> new IllegalArgumentException("해당 슬롯이 존재 하지 않습니다"));

		// 유저 조회
		User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다"));

		boolean isOwner = slot.getPopup().getOwner().getId().equals(userId);
		boolean isAdmin = user.getRole() == Role.ROLE_ADMIN;

		// 작성자 확인
		if (!isOwner && !isAdmin) {
			throw new RuntimeException("수정 권한이 없습니다");
		}

		// 중복 슬롯 확인

		Long popupId = slot.getPopup().getId();
		boolean exists = repository.existsByPopupIdAndDateAndStartTimeLessThanAndEndTimeGreaterThanAndIdNot(popupId,
				request.getDate(), request.getEndTime(), request.getStartTime(), slotId);
		if (exists) {
			throw new IllegalStateException("해당 시간에 슬롯이 이미 존재합니다");
		}

		// 새로 수정한 MaxCount보다 현재 CurrentCount보다 작아야함
		if (request.getMaxCount() < slot.getCurrentCount()) {
			throw new IllegalStateException("현재 예약 인원보다 적게 설정할수 없습니다");
		}

		// 슬롯 내용 수정
		slot.setDate(request.getDate());
		slot.setStartTime(request.getStartTime());
		slot.setEndTime(request.getEndTime());
		slot.setMaxCount(request.getMaxCount());

		return SlotMapper.fromEntity(slot);
	}

	// 슬롯 삭제
	@Override
	@Transactional
	public void deleteSlot(Long slotId, Long userId) {

		User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다"));

		PopupReservationSlot slot = repository.findById(slotId)
				.orElseThrow(() -> new IllegalArgumentException("해당 슬롯이 존재하지 않습니다"));

		// 삭제 권한 확인

		boolean isOwner = slot.getPopup().getOwner().getId().equals(userId);
		boolean isAdmin = user.getRole() == Role.ROLE_ADMIN;
		if (!isOwner && !isAdmin) {
			throw new RuntimeException("삭제 권한이 없습니다");
		}

		repository.delete(slot);
	}

	// 슬롯 조회
	@Override
	@Transactional(readOnly = true)
	public PopupReservationSlotResponseDTO getSlot(Long slotId) {

		PopupReservationSlot slot = repository.findById(slotId)
				.orElseThrow(() -> new IllegalArgumentException("해당 슬롯이 존재하지 않습니다"));

		return SlotMapper.fromEntity(slot);
	}

	// 팝업의 슬롯 리스트 조회
	@Override
	@Transactional(readOnly = true)
	public List<PopupReservationSlotResponseDTO> getSlotsByPopup(Long popupId) {
		Popup popup = popupRepository.findById(popupId)
				.orElseThrow(() -> new IllegalArgumentException("해당 팝업이 존재하지 않습니다"));

		return repository.findByPopupIdOrderByDateAscStartTimeAsc(popupId).stream()
				.map(slot -> SlotMapper.fromEntity(slot)).toList();

	}

	// 팝업의 슬롯 날짜 조회
	@Override
	@Transactional
	public List<PopupReservationSlotResponseDTO> getSlotsByPopupAndDate(Long popupId, LocalDate date) {
		popupRepository.findById(popupId).orElseThrow(() -> new IllegalArgumentException("해당 팝업이 존재하지 않습니다"));

		return repository.findByPopupIdAndDateOrderByStartTime(popupId, date).stream()
				.map(slot -> SlotMapper.fromEntity(slot)).toList();
	}
}
