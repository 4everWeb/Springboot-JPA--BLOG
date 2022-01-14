package com.won.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.won.blog.model.RoleType;
import com.won.blog.model.User;
import com.won.blog.repository.UserRepository;

//bean 등록 (IoC)
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encode;
	
	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encode.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
	
	@Transactional
	public void 회원수정(User user) {
		//수정 시에는 Jpa 영속성 컨텍스트에 User 오브젝트를 영속화 시키고
		// 영속화 된 User 오브젝트를 수정
		//select를 해서 User 오브젝트를 db로부터 가져오는 이유는 영속화를 하기위해서
		// 영속화된 오브젝트를 변경하면 db에 update문 날림.
		User persistance = userRepository.findById(user.getId())
				.orElseThrow(()->{
					return new IllegalArgumentException("회원 찾기 실패");
				});
		String rawPassword = user.getPassword();
		String encPassword = encode.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		//회원수정 함수 종료시 = service 종료 = 트랜잭션 종료 = commit을 자동으로 수행 
		// 영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 
		// 변화 된 데이터를 update문을 날려줌 .

	}
	
	
	/**	세션방식
	@Transactional(readOnly = true) //select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
	public User 로그인(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
	**/
	
}
