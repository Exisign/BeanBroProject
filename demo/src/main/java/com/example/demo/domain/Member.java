package com.example.demo.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

//글번호, 제목, 작성일, 내용, 글쓴이(닉네임), 아이디, 조회수, 글수정일, 
@Data
@Entity
public class Member{

	//게시판유형(타입식별자)
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int member_sq;
	
	@Column(nullable=false)
	private String MemberId;
	
	/*
	 * @OneToMany(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "member_sq") private List <Board> board;
	 */
	
	
	
}
