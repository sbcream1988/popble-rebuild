package com.example.demo.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//팝업스토어 시간 슬롯
@Entity
@Table(
		name = "popup_reservation_slot", 
		//같은 팝업에 같은 시간 같은 날짜 중복 방지
		uniqueConstraints = @UniqueConstraint(columnNames = {"popup_id","date","startTime"}))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PopupReservationSlot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "popup_reservation_slot_id")
	private Long id;
	
	// 팝업
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "popup_id")
	private Popup popup;
	
	// 예약 날짜
	private LocalDate date;
	
	// 시작시간
	private LocalTime startTime;
	
	// 종료시간
	private LocalTime endTime;
	
	// 해당 시간 최대 인원
	private int maxCount;
	
	// 현재 예약 인원(기본 0)
	private int currentCount = 0;
	
	//예약 가능 여부(현재 예약 인원 요청인원 >= 최대 count보다 작게)
	public boolean isAvailable(int requestCount) {
		return currentCount + requestCount <=maxCount;
	}
	
	public void increaseCount(int count) {
		this.currentCount += count;
	}
}
