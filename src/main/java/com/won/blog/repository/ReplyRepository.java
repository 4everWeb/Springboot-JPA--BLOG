package com.won.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.won.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{
	
}
