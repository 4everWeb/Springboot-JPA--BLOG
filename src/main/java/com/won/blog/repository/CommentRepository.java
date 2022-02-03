package com.won.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.won.blog.model.Reply;

public interface CommentRepository extends JpaRepository<Reply, Integer>{
	
}
