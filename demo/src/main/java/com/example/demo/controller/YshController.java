package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ysh")
public class YshController {

	@RequestMapping("/yshBoard")
	public String yshList(){
		return "yshBoard";
	}
}
