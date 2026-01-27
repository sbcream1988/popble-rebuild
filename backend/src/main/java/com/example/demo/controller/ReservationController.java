package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.example.demo.security.CustomUserDetails;
import com.example.demo.service.ReservationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationController {

	
	private final ReservationService reservationService;
	
	//예약 생성
	@PostMapping("/")
	public ResponseEntity<ReservationResponseDTO> createReserve(@RequestBody ReservationRequestDTO request, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
		
		return ResponseEntity.ok(reservationService.createReserve(request,customUserDetails.getUserId()));
	}
	
	//예약 수정
	@PatchMapping("/{reserveId}")
	public ResponseEntity<ReservationResponseDTO> editReserve(@PathVariable(name = "reserveId") Long reserveId, @RequestBody ReservationRequestDTO request, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
		
		return ResponseEntity.ok(reservationService.editReserve(reserveId, request, customUserDetails.getUserId())) ;
	}
	
	//예약 삭제
	@DeleteMapping("/{reserveId}")
	public ResponseEntity<String> cancelReserve(@PathVariable(name = "reserveId") Long reserveId, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
		
		reservationService.cancelReserve(reserveId, customUserDetails.getUserId());
		
		return ResponseEntity.ok("예약이 취소되었습니다");
	}
	
	@GetMapping("/{reserveId}")
	public ResponseEntity<ReservationResponseDTO> getReserve(@PathVariable("reserveId") Long reserveId,@AuthenticationPrincipal CustomUserDetails customUserDetails) {
		
		return ResponseEntity.ok(reservationService.getReserve(reserveId, customUserDetails.getUserId()));
	}
	
	@PreAuthorize("hasRole('ROLE_COMPANY') or hasRole('ROLE_ADMIN')")
	@GetMapping("/slot/{slotId}")
	public ResponseEntity<List<ReservationResponseDTO>> getReserveBySlot(@PathVariable(name = "slotId") Long slotId){
		
		return ResponseEntity.ok(reservationService.getReserveBySlot(slotId));
	}
}
