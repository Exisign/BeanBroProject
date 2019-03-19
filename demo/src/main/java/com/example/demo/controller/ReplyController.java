package com.example.demo.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Board;
import com.example.demo.domain.BoardReply;
import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.BoardReplySevice;
import com.example.demo.service.BoardService;

@RestController
@RequestMapping("/reply/*")
public class ReplyController {

	@Autowired
	private BoardService boardservice;
	
	@Autowired
	private BoardReplySevice boardreplyservice;
	
	@Autowired
	private MemberRepository memberrepository;
	
	@GetMapping("/{boardNo}")
	public ResponseEntity<List<BoardReply>> getReplies(@PathVariable("boardNo") int boardNo, Model model) {
		
		Board board = boardservice.findBoard(boardNo);	// 댓글을 등록하는 board객체를 찾는다.
		
		return new ResponseEntity<>(getListByBoard(board), HttpStatus.CREATED);
	}
	
	
	@Transactional
	@PostMapping("/{boardNo}")
	public ResponseEntity<List<BoardReply>> addReply(@PathVariable("boardNo") int boardNo, @RequestBody BoardReply boardreply, Model model) {
		
		Board board = boardservice.findBoard(boardNo);	// 댓글을 등록하는 board객체를 찾는다.
		boardreply.setBoard(board); 
		Member member = memberrepository.findById(2).get();
		boardreply.setMember(member);
		
		boardreplyservice.dataSave(boardreply);
		
		return new ResponseEntity<>(getListByBoard(board), HttpStatus.CREATED);
	}
	
	private List<BoardReply> getListByBoard(Board board) throws RuntimeException {
		return boardreplyservice.allReplyList(board.getBoardNo());
	}
	
}
