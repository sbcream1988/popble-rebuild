package com.example.demo.mapper;

import java.time.LocalDate;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.domain.User;
import com.example.demo.dto.MyPageRequestDTO;
import com.example.demo.dto.MyPageResponseDTO;
import com.example.demo.dto.UserDTO;

public class UserMapper {

	// DTO -> Entity
	// DB저장 또는 수정시에 사용
	public static User toEntity(UserDTO userDTO, PasswordEncoder passwordEncoder) {
		return User.builder()
				.email(userDTO.getEmail())
				.password(passwordEncoder.encode(userDTO.getPassword()))
				.role(userDTO.getRole())
				.nickname(userDTO.getNickname())
				.profileImage(userDTO.getProfileImage())
				.birthday(userDTO.getBirthday())
				.gender(userDTO.getGender())
				.phoneNumber(userDTO.getPhoneNumber())
				.address(userDTO.getAddress())
				.provider(userDTO.getProvider())
				.providerId(userDTO.getProviderId())
				.createAt(LocalDate.now())
				.build();
	}
	
	// Entity -> DTO
	// 조회 또는 반환 시에 사용
	public static UserDTO toDTO(User user) {
		return UserDTO.builder()
					.userId(user.getId())
					.email(user.getEmail())
					.role(user.getRole())
					.nickname(user.getNickname())
					.profileImage(user.getProfileImage())
					.birthday(user.getBirthday())
					.gender(user.getGender())
					.phoneNumber(user.getPhoneNumber())
					.address(user.getAddress())
					.provider(user.getProvider())
					.providerId(user.getProviderId())
					.createAt(user.getCreateAt())
					.build();
	}
	
	// ---------------myPage 용--------------	
	public static MyPageResponseDTO toMyPageDTO(User user) {
		return MyPageResponseDTO.builder()
				.email(user.getEmail())
				.nickname(user.getNickname())
				.profileImage(user.getProfileImage())
				.phoneNumber(user.getPhoneNumber())
				.address(user.getAddress())
				.build();
	}
}
