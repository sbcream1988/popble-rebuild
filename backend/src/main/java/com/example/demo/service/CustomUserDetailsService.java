package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	
	//email로그인시 사용되는
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
			return userRepository.findByEmail(username)
					.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다"));
	}
	
	//jwt 검증 후 userId로 user조회
	public UserDetails loadByUserId(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다"));
	}
}
