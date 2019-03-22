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
import org.springframework.web.bind.annotation.RequestParam;
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
		
		System.out.println("댓글깊이: " + boardreply.getDepth());
		System.out.println("댓글번호: " + boardreply.getReplyNo());
		
		Board board = boardservice.findBoard(boardNo);	// 댓글을 등록하는 board객체를 찾는다.	
		
		switch(boardreply.getDepth()) {
		case 0: 
			Member member = memberrepository.findById(1).get();
			boardreply.setBoard(board); 
			boardreply.setMember(member);
			boardreplyservice.dataSave(boardreply);		// 일단 한 번 저장하여 AUTOIncrement pk를 얻는다.
			boardreply.setParentId(boardreply.getReplyNo());	// pk를 parentId로 저장. 자기 자신이 parent	
			boardreplyservice.dataSave(boardreply);		// 재저장. 이 과정 안거치면 parentId가 0으로 자꾸 저장됨.	
		break;
		case 1:
			BoardReply reReply = new BoardReply();	//대댓글 객체 생성.
			Member replyMember = memberrepository.findById(2).get(); // 임시로 멤버객체 생성.
			reReply.setMember(replyMember); // 대댓글에 작성자 객체 삽입.
			reReply.setBoard(board); // 대댓글에 board객체 삽입.
			System.out.println("페런츠넘버: " + boardreply.getReplyNo());
			reReply.setParentId(boardreply.getReplyNo());
			reReply.setDepth(1); // 대댓글 깊이 삽입.
			reReply.setReplyContent(boardreply.getReplyContent()); // 대댓글 내용 삽입.
			boardreplyservice.dataSave(reReply);
		break;
		}
		
		board.setReplyCount(board.getReplyCount() + 1);
		boardservice.dataSave(board); //댓글갯수추가

		List<BoardReply> boardreplylist = getListByBoard(board);
		
		return new ResponseEntity<>(boardreplylist, HttpStatus.CREATED);
	}
	
	private List<BoardReply> getListByBoard(Board board) throws RuntimeException {
		return boardreplyservice.allReplyList(board.getBoardNo());
	}
	
	  @Transactional 
	  @PostMapping("/re/{boardNo}") public ResponseEntity<List<BoardReply>>
	  addReReply(@PathVariable("boardNo") int boardNo, @RequestBody BoardReply boardreply, Model model) {
	  
      BoardReply reReply = new BoardReply();
	  reReply.setParentId(boardreply.getReplyNo()); // 대댓글의 parent댓글을 parentid를 삽입.
	  Board board = boardservice.findBoard(boardNo); // 댓글을 등록하는 board객체를 찾는다.
	  board.setReplyCount(board.getReplyCount() + 1);
	  boardservice.dataSave(board); //댓글갯수추가
	  reReply.setBoard(board); // 대댓글에 board객체 삽입.
	  
	  Member member = memberrepository.findById(2).get(); // 임시로 멤버객체 생성.
	  reReply.setMember(member); // 대댓글에 작성자 객체 삽입.
	  reReply.setReplyContent(boardreply.getReplyContent()); // 대댓글 내용 삽입.
	  reReply.setDepth(1); // 대댓글 깊이 삽입.
	  
	  System.out.println("작동중"); System.out.println("reReply: " + reReply);
	  boardreplyservice.dataSave(reReply);
	  
	  List<BoardReply> boardreplylist = getListByBoard(board);
	  
	  return new ResponseEntity<>(boardreplylist, HttpStatus.CREATED); 
	  
	  }
	 
	
}
