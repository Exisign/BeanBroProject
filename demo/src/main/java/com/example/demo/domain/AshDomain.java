package com.example.demo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

//글번호, 제목, 작성일, 내용, 글쓴이(닉네임), 아이디, 조회수, 글수정일, 
@Data
@Table(name="defaultTable")
@Entity
public class AshDomain{

	//게시판유형(타입식별자)
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int number;
	
	@Column(nullable=false)
	private String boardType;
	
	@Column(nullable=false)
	private String title;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date writeDate = new Date();
	
	@Column(nullable=false, length=1000)
	private String content;
	
	@Column
	private String nickname;
	
	@Column
	private String id;
	
	@Column
	private int hit = 0;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate = new Date();
	
}
