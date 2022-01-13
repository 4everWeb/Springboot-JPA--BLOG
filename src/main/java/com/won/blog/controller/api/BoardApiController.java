package com.won.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.won.blog.config.auth.PrincipalDetail;
import com.won.blog.dto.ResponseDto;
import com.won.blog.model.Board;
import com.won.blog.model.User;
import com.won.blog.service.BoardService;
import com.won.blog.service.UserService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		boardService.글쓰기(board, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id) {
		boardService.글삭제하기(id);
		System.out.println(">>> id:"+id);
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