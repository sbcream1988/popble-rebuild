package com.example.demo.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//예약
@Entity
@Table(name = "reservation")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_id")
	private Long id;
	
	//팝업
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "popup_id")
	private Popup popup;
	
	//예약 시간
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "popup_reservation_slot_id")
	private PopupReservationSlot popupReservationSlot;
	
	//예약자(비회원 예약 가능하게 nullable)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id",nullable = true)
	private User reserver;
	
	//예약자 이름(실제 예약자 이름)
	private String reserverName;
	
	//전화번호
	private String phoneNumber;
	
	//예약 인원
	private int count;
	
	//예약 생성 시간
	private LocalDateTime createdAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
	}
}
