package com.won.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.won.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {
	//세션 찾는법  - 파라미터
	//@AuthenticationPrincipal PrincipalDetail principal
	//principal.getUsername()
	@GetMapping({ "", "/" })
	public String index() {
		// /WEB-INF/views/index.jsp
		return "index";
	}
}