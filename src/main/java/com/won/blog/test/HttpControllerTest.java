package com.won.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {
	
	private static final String TAG ="HttpControllerTest";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().id(123).password("ddddd").build();
		System.out.println(TAG+"getter"+m.getId());
		m.setId(5000);
		System.out.println(TAG+"getter"+m.getId());
		Member m2 = new Member();
		
		return "lombok test 완료";
	}
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get : "+m.getId()+","+m.getPassword()+","+m.getUsername()+","+m.getEmail();
	}
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {
		// application/json  << MessageConverter .
		// json으로 전송하면 바로객체에 맵핑
		//postman > Body > raw > Json 으로 보내야함
		return "post : "+m.getId()+","+m.getPassword()+","+m.getUsername()+","+m.getEmail();
	}
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put : "+m.getId()+","+m.getPassword()+","+m.getUsername()+","+m.getEmail();
	}
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete";
	}

}
