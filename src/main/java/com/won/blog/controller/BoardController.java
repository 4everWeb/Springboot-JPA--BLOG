package com.won.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.won.blog.config.auth.PrincipalDetail;
import com.won.blog.service.BoardService;


@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//세션 찾는법  - 파라미터
	//@AuthenticationPrincipal PrincipalDetail principal
	//principal.getUsername()
	@GetMapping({ "", "/" })
	public String index(Model mdoel,@PageableDefault(size=3, sort="id", direction=Sort.Direction.DESC) Pageable pageable) {
		// /WEB-INF/views/index.jsp
		mdoel.addAttribute("boards",boardService.글목록(pageable));
		return "index"; //viewResolver 
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "board/detailForm";
	}
	
	
	//User권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
}