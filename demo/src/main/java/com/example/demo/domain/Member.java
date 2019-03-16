package com.example.demo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class Member {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int memberNo;
	
	@Column(nullable=false)
	private String memberId;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date registerDate = new Date();
	
	
}
