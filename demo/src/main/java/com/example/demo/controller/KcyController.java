package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.BoardRepository;

@Controller
@RequestMapping("/kcy")
public class KcyController {
	
	@Autowired
	BoardRepository boardRepository;
	
	@RequestMapping("/kcyList")
	public String kcyList(Model model){
		model.addAttribute("list", boardRepository.findAll());
		return "kcyBoard";
	}

}
