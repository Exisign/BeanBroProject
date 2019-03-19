package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;

import lombok.Data;

@Table(name="file")
@Data
public class BoardFile {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int fileId;
	
	@Column(name="board_id")
	int boardId;
	
	@Column(name="file_origin_name")
	String fileOriginName;
	
	@Column(name="file_enripte_name")
	String fileEncriptName;
	
	@Column(name="number")
	int number;
}
