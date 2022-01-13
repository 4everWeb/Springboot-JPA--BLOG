package com.won.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.won.blog.model.User;

import lombok.Getter;
// 1. 인터셉터 . 로그인 완료 userDetails 오브젝트를 저장 (principal)
// 2. 시큐리티 세션에 저장 .
@Getter
public class PrincipalDetail implements UserDetails {
	// userDetails 타입 user object ~..

	private User user; // 콤포지션 .
	
	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지 리턴 (true 만료 x)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정이 잠겨있지 않았는지 리턴 (true 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호 만료
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정 활성화
	@Override
	public boolean isEnabled() {
		return true;
	}

	// 권한을 리턴
	// 타입이 컬렉션
	// 권한 하나 (for문 보류)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collertors = new ArrayList<>();
		collertors.add(() -> {return "ROLE_" + user.getRole();}); // ROLE_USER
		
		return collertors;
	}

}
