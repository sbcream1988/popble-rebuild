package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MyPageRequestDTO;
import com.example.demo.dto.MyPageResponseDTO;
import com.example.demo.dto.PasswordChangeDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	// 회원 가입
	@PostMapping("/signup")
	public ResponseEntity<UserDTO> signup(@RequestBody UserDTO userDTO) {
		return ResponseEntity.ok(userService.signup(userDTO));
	}
	
	// 회원 정보 수정
	@PatchMapping("/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable(name = "userId") Long id, UserDTO userDTO) {
		return ResponseEntity.ok(userService.updateUser(id, userDTO));
	}
	
	// 회원 삭제
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable(name = "userId") Long id) {
		userService.deleteUser(id);
		return ResponseEntity.ok("회원 삭제가 완료되었습니다");
	}
	
	// 회원 조회
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable(name = "userId") Long id){
		return ResponseEntity.ok(userService.getUser(id));
	}
	
	// 회원 리스트 조회
	@GetMapping("/list")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	//-------내 정보 보기---------
	
	//내 정보 조회
	@GetMapping("/me")
	public ResponseEntity<MyPageResponseDTO> getMyInfo(@AuthenticationPrincipal CustomUserDetails userDetails){
		return ResponseEntity.ok(userService.getMyInfo(userDetails.getUserId()));
	}
	
	//내 정보 수정
	@PutMapping("/me")
	public ResponseEntity<MyPageResponseDTO> updateMyInfo(@RequestBody MyPageRequestDTO myPageRequestDTO, @AuthenticationPrincipal CustomUserDetails userDetails){
		return ResponseEntity.ok(userService.updateMyInfo(myPageRequestDTO, userDetails.getUserId()));
	}
	
	//비밀번호 변경
	@PutMapping("/me/password")
	public ResponseEntity<UserDTO> changePassword(@RequestBody PasswordChangeDTO changeDTO, @AuthenticationPrincipal CustomUserDetails userDetails){
		return ResponseEntity.ok(userService.changePassword(changeDTO, userDetails.getUserId()));
	}
	
	//회원 탈퇴
	@DeleteMapping("/me")
	public ResponseEntity<String> withdrawMember(@AuthenticationPrincipal CustomUserDetails userDetails){
		userService.deleteUser(userDetails.getUserId());
		return ResponseEntity.ok("회원 탈퇴 성공!");
	}
}
