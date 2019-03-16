package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Board;
import com.example.demo.repository.BoardRepository;

@Controller
public class SampleController {

	@Autowired
	BoardRepository boardrepository;
	
	@RequestMapping("/sample")
	public String sample() {
		
		/*
		 * Board board = new Board();
		 * 
		 * board.setContent("안녕"); board.setTitle("꿈틀"); board.setBoardType("자유");
		 * 
		 * boardrepository.save(board);
		 */
		
		/*
		 * query dsl
		 * Board board = boardrepository.findById(1).get(); // select * from board where
		 * number = 1; System.out.println(board);
		 * 
		 * board.setContent("바이"); boardrepository.save(board);
		 */
		
		
		boardrepository.deleteById(1);
		//select * from board;
		
		
		return "redirect:/test/main";
	}

	
}
