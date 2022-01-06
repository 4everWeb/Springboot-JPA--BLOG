package com.won.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
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
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int id;
	
	@Column(nullable = false,length =100)
	private String title;
	
	@Lob
	private String content; //썸머노트 라이브러리 <html> 태그 섞여서  디자인되서 대용량이어야함 (@Lob)
	
	@ColumnDefault("0")
	private int count;
	
	@ManyToOne (fetch = FetchType.EAGER)//Many = Board , User = One  한명의 유저가 여러개의 게시글 작성
	@JoinColumn(name="userId")
	private User user;
	//private int userId 
	//ORm에선 오브젝트 저장
	//자동으로 포린키 생성
	//eager 전략 무조건 가져옴 lazy 전략 필요할때 가져옴
	@OneToMany(mappedBy = "board", fetch=FetchType.LAZY)  // mappedBy 연관관계의 주인이 아니다(난 fk가 아니다) db에 컬럼을 만들지 마세요
	private List<Reply> reply;
	//조인 - 하나의 보드에 여러개의 리플 
	// 포린키가 필요없음  >> replyId 가 여러개가 들어 갈 수도 있음
	
	@CreationTimestamp
	private Timestamp createDate;
}
