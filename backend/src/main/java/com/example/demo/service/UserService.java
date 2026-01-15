package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserDTO;

public interface UserService {
	
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
	
}
