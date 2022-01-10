package com.won.blog.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.won.blog.model.User;

//DAO
//자동으로 bean 등록 
//@Repository 생략가능 
public interface UserRepository extends JpaRepository<User, Integer>{
	

}
