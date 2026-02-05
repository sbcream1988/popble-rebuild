package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.MyPageRequestDTO;
import com.example.demo.dto.MyPageResponseDTO;
import com.example.demo.dto.PasswordChangeDTO;
import com.example.demo.dto.UserDTO;

public interface UserService {
	//------------관리자 영역---------------
	
	// 회원 가입
	UserDTO signup(UserDTO userDTO);
	
	// 회원 수정
	UserDTO updateUser(Long id, UserDTO userDTO);
	
	// 회원 삭제
	void deleteUser(Long id);
	
	// 회원 조회
	UserDTO getUser(Long id);
	
	// 전체 회원 조회
	List<UserDTO> getAllUsers();
	
	//--------------MyPage 영역 ----------------
	
	// 내 정보 조회
	MyPageResponseDTO getMyInfo(Long userId);
	
	// 내 정보 수정
	MyPageResponseDTO updateMyInfo(MyPageRequestDTO requestDTO, Long userId);
	
	// 내 비밀번호 수정
	UserDTO changePassword(PasswordChangeDTO changeDTO, Long userId);
	
	// 회원 탈퇴
	void withdrawMember(Long userId);
	
	
}
