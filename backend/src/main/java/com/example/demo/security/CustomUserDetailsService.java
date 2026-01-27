package com.example.demo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	
	//email로그인시 사용되는
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
			
		User user = userRepository.findByEmail(email)
					.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다"));
		
		return new CustomUserDetails(user);
	}
	
	//jwt 검증 후 userId로 user조회
	public UserDetails loadByUserId(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다"));
		
		return new CustomUserDetails(user);
	}
}
