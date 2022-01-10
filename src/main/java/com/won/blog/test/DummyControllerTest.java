package com.won.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.won.blog.model.RoleType;
import com.won.blog.model.User;
import com.won.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired // 의존성 주입
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
		userRepository.deleteById(id);
		} catch(EmptyResultDataAccessException e ) {
			return "삭제에 실패하였습니다. 해당 id는 데이터베이스에 없습니다.";
		}
		return "삭제되었습니다. id:" + id;
	}
	
	
	// email, password
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id:" + id);
		System.out.println("password:" +requestUser.getPassword());
		System.out.println("Email:" +requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//save id를 전달하지 않으면 insert
		//id를 전달하면 해당 id에 대한 데이터가 있으면 update
		//id를 전달하면 해당 id에 대한 데이터가 없으면 insert
		//userRepository.save(user);
		
		//더티 체킹
		//영속성 컨텍스트 .. db에 가기전에 1차캐시 확인 , 부하를 줄임
		//함수 종료시에 트랜잭션이 자동 commit 
		//영속성 컨텍스트에 있던 데이터가 커밋
		return user;
	}
	
	//한페이지당 2건의 데이터를 리턴 받아 보기 
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable){
		Page<User> paginUser = userRepository.findAll(pageable);
		
		List<User> users = paginUser.getContent();
		return users;
	}
 	
	//리스트 조회
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	//아이디 조회 id 주소로 파라미터 전달 가능
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) { 
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
				@Override
				public IllegalArgumentException get() {
					// TODO Auto-generated method stub
					return new IllegalArgumentException("해당 유저는 없습니다. id:"+ id);
				}
			} );
		return user;
		//요청 : 웹브라우저
		//user 객체 = 자바 오브젝트
		//변환 >> json (웹브라우저가 이해할 수 있는 데이터)
		//만약에 자바 오브젝터를 리턴하면 messageConverter가 jackson 라이브러리를 호출해서
		//user 오브젝트를 json으로 변환해서 브라우저에게 던져줌
	 /* userRepository.findById(id).orElseGet(new Supplier<User>() { //만약 id 값이 없으면
	 * 아래 메서드 실행 >> 빈객체를 user에 넣어줌 (null은아님)
	 * 
	 * @Override public User get() { return new User(); } });
	 */
		// findById 의 return 타입이 user는 아님 
		// why? 못찾으면 null 값을 리턴 하기 때문에 Optional 로 가져옴
		//null 판단은 개발자가 .
		
	}
	
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("username:"+user.getUsername() );
		System.out.println("password:"+user.getPassword() );
		System.out.println("email:"+ user.getEmail());

		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입 완료되었습니다";
	}
}
