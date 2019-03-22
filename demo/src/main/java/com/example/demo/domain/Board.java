package com.example.demo.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int boardNo;
	
	@ManyToOne
	@JoinColumn(name = "memberNo")
	private Member member;
	
	@Column(nullable=false, length = 5)
	private String boardType;
		
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false, length = 1000)
	private String content;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date writeDate = new Date();
	
	@Column(nullable=false)
	private int hit = 0;
	
	@Column(nullable=false)
	private int replyCount = 0;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date modifyDate = new Date();

}
