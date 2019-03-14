package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lmy")
public class LmyController {
	
	@RequestMapping("/lmyList")
	public String lmyList(){
		return "lmyBoard";
	}

}
