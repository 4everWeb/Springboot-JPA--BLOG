package com.won.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	
	@GetMapping("/temp/ee")
	public String tempHome() {
		System.out.println("temp/home");
		return "home";
	}
}
