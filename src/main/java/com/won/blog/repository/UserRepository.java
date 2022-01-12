package com.won.blog.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.won.blog.model.User;

//DAO
//자동으로 bean 등록 
//@Repository 생략가능 
public interface UserRepository extends JpaRepository<User, Integer>{
	//JPA Naming 전략
	 User findByUsernameAndPassword(String username, String password);
	// SELECT * FROM user WHERE username =? and password = ?;
	
	 //@Query(value="SELECT * FROM user WHERE username =? AND password =?",nativeQuery = true)
	// User login(String username , String password)
}

