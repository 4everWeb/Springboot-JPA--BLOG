package com.won.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.won.blog.dto.ResponseDto;
import com.won.blog.model.User;
import com.won.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController: save 호출");
		userService.회원가입(user);
		
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