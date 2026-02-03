package com.example.demo.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private String email;
	private String password;
	private Role role;
	private String nickname;
	
	public CustomUserDetails(User user) {
		this.userId = user.getId();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.role = user.getRole();
		this.nickname = user.getNickname();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	public Long getUserId() {
		return userId;
	}
	
	@Override
	public String getUsername() {
		return email;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	public Role getRole() {
		return role;
	}
	
	public String getNickname() {
		return nickname;
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
