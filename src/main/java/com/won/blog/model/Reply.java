package com.won.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int id;
	
	@Column(nullable = false,length =200)
	private String content;
	
	@ManyToOne
	@JoinColumn(name="boardId")
	private Board board;
	//여러개의 게시글은 하나의 유저가 답변
	//ex one to one 하나의  게시글 하나의 답변
	//ex one to many 하나의 답변은 여러개의 게시글 말안됌x
	// 알아서 Board 테이블에 연결
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	// 하나의 답변은 하나의 유저 (x)
	//여러개의 답변을 하나의 유저 >> many to one (o)
	//알아서 User 테이블에 연결
	
	@CreationTimestamp
	private Timestamp createDate;
}
