package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Board;
import com.example.demo.repository.BoardRepository;

@Controller
@RequestMapping("/ash")
public class AshController {

	@Autowired
	BoardRepository boardRepository;
	
	@RequestMapping("/ashBoard")
	public String ashList(Model model){
		
		List<Board> board = boardRepository.findAll();
		
		model.addAttribute("list",board);
		
		return "ash/ashBoard";
	}
	
	@RequestMapping("/writeForm")
	public String writeForm(){
		return "ash/ashWriteForm";
		
	}
	
}
