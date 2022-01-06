package com.won.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//com.won.blog 패키지 이하 스캔 >> 메모리에 new
//특정 어노테이션 붙어있 클래스 파일들을 new 해서(ioc) 스프링 컨테이너에 관리
@RestController
public class BlogControllerTest {
	
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>spring boot</h1>";
	}
}
