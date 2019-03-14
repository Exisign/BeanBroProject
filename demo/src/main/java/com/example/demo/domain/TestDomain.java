package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

 
@Data
@Entity
public class TestDomain {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int testId;
	
	@Column(nullable=false)
	private String content;
	
	@Column
	private String test;
	
	
	
	
}
