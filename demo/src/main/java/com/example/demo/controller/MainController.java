package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.TestDomain;
import com.example.demo.service.TestService;

@Controller
@RequestMapping("/test")
public class MainController {
	@Autowired
	TestService testservice;
	
	@RequestMapping("/main")
	public String main(){
		return "Main";
	}
	
	@RequestMapping("/signup")
	public String goSignup(@ModelAttribute TestDomain testdomain) {

		return "test/signup";
	}
	
	@RequestMapping("/insert")
	public String insertData(@ModelAttribute TestDomain testdomain) {
		testservice.dataSave(testdomain);	//데이터 insert를 위해 적어줌. (db에서 insert into from table 어쩌고 해서 넣는 것. insert문을 까먹었네유..)
		return "redirect:/test/allList";
	}
	
	@RequestMapping("/allList")
	public String selectList(Model model) {
		List<TestDomain> testdomain = testservice.allList();	// 데이터 전체 조회. select * from testdomain과 동일.
		model.addAttribute("testdomain", testdomain);
		return "test/allList";
	}
	
}
