package com.won.blog.controller.api;



import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.won.blog.dto.ResponseDto;
import com.won.blog.model.KakaoProfile;
import com.won.blog.model.OAuthToken;
import com.won.blog.model.User;
import com.won.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController: save 호출");
		userService.회원가입(user);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user) {
		System.out.println("UserApiController: update 호출");
		userService.회원수정(user);
		// 여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경이 됐음
		// 하지만 세션값은 변경되지 않은 상태이기 때문에
		// 다시 회원정보를 확인해도 변하지 않은 것으로 보임
		// 직접 세션 값을 변경해줄 것!
		//토큰을 authentication 객체에 넣어줘야함
		//authentication 객체 를 세션에 넣어줘야함
		//Security contextHolder
		//***** 
		// 해당 방법으로는 안됌 
		// 컨텍스트홀더에 인증 객체를 직접 교체하는건 안됌 .
		// 해결 >> 매니저에 토큰값 넣기  
		//*****
		//세션등록 - 
		System.out.println("UserApiController: 영속화 객체 커밋");
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}

}




/**
 * 세션 방식
@PostMapping("/api/user/login")
public ResponseDto<Integer> login(@RequestBody User user,HttpSession session){
	// 세션은 파라미터 지우고 DI도 가능
	// @Autowired
	//	private HttpSession session;
	System.out.println("UserApiController: login 호출");
	User principal = userService.로그인(user);
	
	if(principal != null) {
		session.setAttribute("principal", principal);
	}
	
	return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
}
**/