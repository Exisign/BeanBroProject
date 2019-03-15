package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ash")
public class AshController {

	@RequestMapping("/ashBoard")
	public String ashList(){
		return "ashBoard";
	}
	
	@RequestMapping("/writeForm")
	public String writeForm(){
		return "ashWriteForm";
		
	}
	
}
