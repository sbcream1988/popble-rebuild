package com.example.demo.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "popup")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Popup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "popup_id")
	private Long id;
	
	//팝업스토어 이름
	private String title;
	
	//팝업스토어 소개
	@Column(columnDefinition = "TEXT")
	private String description;
	
	//팝업스토어 주소
	@Column(nullable = false)
	private String address;
	
	//시작일
	private LocalDate startDate;
	
	//종료일
	private LocalDate endDate;
	
	//가격
	private int price;
	
	//최대 수용 인원(전체)
	private int maxCapacity;
	
	//조회수(기본 0)
	@Column(nullable = false)
	private int viewCount;
	
	//생성시간
	@Column(updatable = false)
	private LocalDateTime createdAt;
	
	//등록자
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User owner;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.viewCount=0;
	}
	
	//예약 가능 시간(배열로 연결)
	@OneToMany(mappedBy = "popup", cascade = CascadeType.ALL, orphanRemoval = true )
	private List<PopupReservationSlot> popupReservationSlots = new ArrayList<>();
	

}
