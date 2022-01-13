package com.won.blog.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.won.blog.model.Board;
import com.won.blog.model.User;
public interface BoardRepository extends JpaRepository<Board, Integer>{


}




