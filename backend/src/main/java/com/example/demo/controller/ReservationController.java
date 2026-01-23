package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ReservationRequestDTO;
import com.example.demo.dto.ReservationResponseDTO;
import com.example.demo.service.ReservationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationController {

	private final ReservationService reservationService;
	
	//예약 생성
	@PostMapping("/")
	public ReservationResponseDTO createReserve(@RequestBody ReservationRequestDTO request) {
		
		return reservationService.createReserve(request);
	}
	
	//예약 수정
	@PatchMapping("/{reserveId}")
	public ReservationResponseDTO editReserve(@PathVariable(name = "reserveId") Long reserveId, @RequestBody ReservationRequestDTO request) {
		
		return reservationService.editReserve(reserveId, request);
	}
	
	//예약 삭제
	@DeleteMapping("/{reserveId}")
	public void cancelReserve(@PathVariable(name = "reserveId") Long reserveId) {
		
		reservationService.cancelReserve(reserveId);
	}
	
	@GetMapping("/{reserveId}")
	public ReservationResponseDTO getReserve(@PathVariable("reserveId") Long reserveId) {
		
		return reservationService.getReserve(reserveId);
	}
	
	@GetMapping("/slot/{slotId}")
	public List<ReservationResponseDTO> getReserveBySlot(@PathVariable(name = "slotId") Long slotId){
		
		return reservationService.getReserveBySlot(slotId);
	}
}
