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
public class BoardReply {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int replyNo;
	
	@ManyToOne
	@JoinColumn(name = "boardNo")
	private Board board;
	
	@Column
	private int parentId;
	
	@Column
	private int depth = 0;
	
	@Column(nullable=false)
	private String replyContent;
	
	@ManyToOne
	@JoinColumn(name = "memberNo")
	private Member member;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date replyDate = new Date();

}
