package com.won.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //User 클래스가 mySql에 테이블 생성
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더패턴 !!
//orm > > java(다른언어) object >> 테이블로 매핑해줌
@DynamicInsert //null인 필드 제외하고 insert @ColumnDefault("user)에 null 값이오면 user로 insert 안됌
//insert 시 null인 필드 제외
public class User {
	
	@Id //primaryKey
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	// 프로젝트에서 연결된 db의 넘버링 전략을 따라감 (오라클 - 시퀀스 , mysql - auto_increment)
 	private int id; //시퀀스, auto_increment
	
	@Column(nullable = false, length = 30)
	private String username; //아이디
	
	@Column(nullable = false, length = 100) 
	private String password; 
	
	@Column(nullable = false, length = 50) 
	private String email;
	
	//@ColumnDefault("'user'")
	//DB는 RolyType 이라는게 없음 
	@Enumerated(EnumType.STRING)
	private RoleType role; 
	// Enum 을 쓰는게 좋음 // admin, user, manager 도메인(범위)으로 설정가능 (오타입력 x)
	
	@CreationTimestamp
	private Timestamp createDate;
}
