package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	// 이메일로 회원 찾기
	Optional<User> findByEmail(String email);
	
	// 닉네임 중복 검사를 위한 메소드
	boolean existsByNickname(String nickname);
}
