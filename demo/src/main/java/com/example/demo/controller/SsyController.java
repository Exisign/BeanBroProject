package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.TestRepository;

@Controller
@RequestMapping("/test")
public class SsyController {
	
	@Autowired
	TestRepository testRepository;
	
	@RequestMapping("/sysBoard")
	public String sysList(Model model){
		
		return "sys/ssyBoard";
	}
	
	
}
