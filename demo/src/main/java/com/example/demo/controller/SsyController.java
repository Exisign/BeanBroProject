package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class SsyController {
	
	@RequestMapping("/sysBoard")
	public String sysList(){
		return "ssyBoard";
	}
	

}
