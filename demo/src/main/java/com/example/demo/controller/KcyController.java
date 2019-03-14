package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kcy")
public class KcyController {
	
	@RequestMapping("/kcyList")
	public String kcyList(){
		return "kcyBoard";
	}

}
