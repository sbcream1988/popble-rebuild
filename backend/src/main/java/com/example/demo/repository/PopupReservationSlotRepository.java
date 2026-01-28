package com.example.demo.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.PopupReservationSlot;

public interface PopupReservationSlotRepository extends JpaRepository<PopupReservationSlot, Long> {

	// 특정날짜조회
	List<PopupReservationSlot> findByPopupIdAndDateOrderByStartTime(Long poopupId, LocalDate date);

	// 슬롯 단건 조회
	Optional<PopupReservationSlot> findById(Long id);

	// 슬롯 중복 체크
	boolean existsByPopupIdAndDateAndStartTimeLessThanAndEndTimeGreaterThan(Long popupId, LocalDate date,
			LocalTime endTime, LocalTime startTime);

	// 슬롯 중복 체크(자신 슬롯 제외)
	boolean existsByPopupIdAndDateAndStartTimeLessThanAndEndTimeGreaterThanAndIdNot(Long popupId, LocalDate date,
			LocalTime endTime, LocalTime startTime, Long slotId);

	// 슬롯 리스트 조회
	List<PopupReservationSlot> findByPopupIdOrderByDateAscStartTimeAsc(Long popupId);
}
