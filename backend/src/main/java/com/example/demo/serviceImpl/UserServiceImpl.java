package com.example.demo.serviceImpl;

import java.util.List;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.dto.MyPageRequestDTO;
import com.example.demo.dto.MyPageResponseDTO;
import com.example.demo.dto.PasswordChangeDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	// 회원 가입
	@Transactional
	@Override
	public UserDTO signup(UserDTO userDTO) {
		
		//닉네임 입력값이 없으면 랜덤으로 생성
		String nickname = userDTO.getNickname();
		if(nickname == null || nickname.isEmpty()) {
			do {
				nickname = generateNickName();
			}while(userRepository.existsByNickname(nickname));
			userDTO.setNickname(nickname);
		}
		
		// DTO -> Entity
		User user = UserMapper.toEntity(userDTO, passwordEncoder);
		if(user.getRole() == null) {
			user.setRole(Role.ROLE_USER);
		}
		
		// DB에 저장
		userRepository.save(user);
		
		// Entity -> DTO
		return UserMapper.toDTO(user);
	}
	
	// 회원 수정
	@Transactional
	@Override
	public UserDTO updateUser(Long id, UserDTO userDTO) {
		User user = userRepository.findById(id)
							.orElseThrow(()->new IllegalArgumentException("해당 아이디의 유저가 존재하지 않습니다."));
		
		user.setNickname(userDTO.getNickname());
		user.setProfileImage(userDTO.getProfileImage());
		user.setBirthday(userDTO.getBirthday());
		user.setPhoneNumber(userDTO.getPhoneNumber());
		user.setAddress(userDTO.getAddress());
		
		if(userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
			user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		}
	
		User updatedUser = userRepository.save(user);
		
		return UserMapper.toDTO(updatedUser);
	}
	
	// 회원 삭제(Ban!!)
	@Transactional
	@Override
	public void deleteUser(Long id) {
		// 회원 정보 찾기
		if(!userRepository.existsById(id)) {
			throw new IllegalArgumentException("해당 아이디의 유저가 존재하지 않습니다.");
		}
		userRepository.deleteById(id);
		
	}
	
	// 회원 조회
	@Override
	public UserDTO getUser(Long id) {
		User user = userRepository.findById(id)
					.orElseThrow(()->new IllegalArgumentException("해당 유저의 아이디가 존재하지 않습니다"));
		return UserMapper.toDTO(user);
	}
	
	// 전체 회원 조회
	@Override
	public List<UserDTO> getAllUsers() {
		return userRepository.findAll()
				.stream()
				.map((user)-> UserMapper.toDTO(user))
				.collect(Collectors.toList());
	}
	
	// 회원 가입 시 nickname을 작성 안할경우 임시 닉네임을 부여
	public String generateNickName() {
		String randomNick = UUID.randomUUID().toString().substring(0,6);
		return "user_" + randomNick;
	}
	
	// -------- MyPage용 기능 ---------
	
	//내 정보 조회
	@Transactional(readOnly = true)
	public MyPageResponseDTO getMyInfo(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 유저의 아이디가 존재하지 않습니다"));
		
		return UserMapper.toMyPageDTO(user);
	}
	
	// 내 정보 수정
	@Override
	@Transactional
	public MyPageResponseDTO updateMyInfo(MyPageRequestDTO requestDTO, Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("해당 유저의 아이디가 존재하지 않습니다"));
		
		//닉네임 변경시 중복 체크
		if(requestDTO.getNickname() != null && !requestDTO.getNickname().equals(user.getNickname()) && userRepository.existsByNickname(requestDTO.getNickname())) {
			throw new IllegalArgumentException("이미 사용 중인 닉네임입니다");
		}
		
		user.setNickname(requestDTO.getNickname());
		user.setPhoneNumber(requestDTO.getPhoneNumber());
		user.setAddress(requestDTO.getAddress());
		user.setProfileImage(requestDTO.getProfileImage());
		
		return UserMapper.toMyPageDTO(user);
	}

	@Override
	@Transactional
	public UserDTO changePassword(PasswordChangeDTO changeDTO, Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("해당 유저의 아이디가 존재하지 않습니다"));
		
		if(!passwordEncoder.matches(changeDTO.getCurrentPassword(), user.getPassword())) {
			throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다");
			
		}
		
		//비밀번호 검증
		if(changeDTO.getNewPassword().length()<8) {
			throw new IllegalArgumentException("비밀번호는 최소 8자 이상이어야합니다");
		}
		if(passwordEncoder.matches(changeDTO.getNewPassword(), user.getPassword())) {
			throw new IllegalArgumentException("기존 비밀번호와 동일합니다");
		}
		
		user.setPassword(passwordEncoder.encode(changeDTO.getNewPassword()));
		
		return UserMapper.toDTO(user);
	}

	// 회원 탈퇴
	@Override
	public void withdrawMember(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("해당 유저의 아이디가 존재하지 않습니다"));
		
		userRepository.delete(user);
		
	}
	
	

}
