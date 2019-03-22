package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.BoardRepository;

@Controller
@RequestMapping("/kcy")
public class KcyController {
	
	@Autowired
	BoardRepository boardRepository;
	
	Logger logger = LoggerFactory.getLogger(KcyController.class);
	
	@RequestMapping("/kcyList")
	public String kcyList(Model model){
		model.addAttribute("boardList", boardRepository.findAll());
		logger.info("##보드 테스트11 : "+boardRepository.findAll().toString());
		return "/kcy/kcyBoard";
	}
//	/kcy/readPosting?boardNo=${list.boardNo}
	@RequestMapping("/readPosting")
	public String kcyReading(Model model, @RequestParam int boardNo) {
		model.addAttribute("board", boardRepository.findById(boardNo).get());
		logger.info("##보드 테스트 : "+boardRepository.findById(boardNo).toString());
		return "/kcy/readPosting";
	}
	
	@RequestMapping("/modifyPosting")
	public String kcyModify() {
		return null;
	}

}
