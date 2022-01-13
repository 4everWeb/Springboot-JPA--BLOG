package com.won.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.won.blog.model.User;
import com.won.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService{
	
	@Autowired 
	private UserRepository userRepository;
	
	// 스프링이 로그인 요청을 가로챔 (username ,password)
	// username이 db에 있는지만 확인
	// password 는 시큐리티가 알아서 함
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
			.orElseThrow(()->{
				return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.");
			});
		return new PrincipalDetail(principal); // 시큐리티 세션에 유저 정보를 저장.
	}
}

