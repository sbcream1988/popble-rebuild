package com.example.demo.domain;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 유저 도메인

@Entity
@Table(name = "Users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	
	// 이메일 겸 아이디
	@Column(nullable = false, unique = true)
	@Email
	@NotEmpty
	private String email;
	
	// 비밀번호
	@Column(nullable = false)
	@NotBlank
	private String password;
	
	// 권한
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;
	
	// 닉네임	
	@Column(unique = true)
	private String nickname;
	
	// 프로필 사진
	private String profileImage;
	
	// 생일
	private LocalDate birthday;
	
	// 성별
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	// 전화번호
	private String phoneNumber;
	
	// 주소
	private String address;
	
	// OAuth관련
	// 공급업체
	@Column(name = "provider")
	@Enumerated(EnumType.STRING)
	private Provider provider;
	
	// 아이디
	private String providerId;
	
	// 가입일
	@Column(updatable = false) //가입일은 수정 불가능하게
	private LocalDate createAt = LocalDate.now();
	
	//
	
	@Override 
	public Collection<? extends GrantedAuthority> getAuthorities(){
		return List.of(()-> role.name());//기본은 빈 권한
		
	}
	
	@Override
	public String getUsername() {
		return this.email;//로그인할 기준
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
}
