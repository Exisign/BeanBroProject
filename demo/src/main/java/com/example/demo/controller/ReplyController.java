package com.example.demo.controller;

import java.util.Date;
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
		
		System.out.println("--댓글등록--");
		System.out.println("댓글깊이: " + boardreply.getDepth());
		System.out.println("댓글번호: " + boardreply.getReplyNo());
		
		Board board = boardservice.findBoard(boardNo);	// 댓글을 등록하는 board객체를 찾는다.	
		
		switch(boardreply.getDepth()) {		// depth = 0 -> 댓글, depth = 1 -> 대댓글
		case 0: 	// 댓글등록
			Member member = memberrepository.findById(1).get();
			boardreply.setBoard(board); 
			boardreply.setMember(member);
			boardreplyservice.dataSave(boardreply);		// 일단 한 번 저장하여 AUTOIncrement pk를 얻는다.
			boardreply.setParentId(boardreply.getReplyNo());	// pk를 parentId로 저장. 자기 자신이 parent	
			boardreplyservice.dataSave(boardreply);		// 재저장. 이 과정 안거치면 parentId가 0으로 자꾸 저장됨.	
		break;
		case 1:		// 대댓글등록
			BoardReply reReply = new BoardReply();	//대댓글 객체 생성.
			Member replyMember = memberrepository.findById(2).get(); // 임시로 멤버객체 생성.
			reReply.setMember(replyMember); // 대댓글에 작성자 객체 삽입.
			reReply.setBoard(board); // 대댓글에 board객체 삽입.
			reReply.setReplyContent(boardreply.getReplyContent());
			System.out.println("페런츠넘버: " + boardreply.getReplyNo());
			reReply.setParentId(boardreply.getReplyNo());
			reReply.setDepth(1);	// 대댓글
			boardreplyservice.dataSave(reReply);
		break;
		}
		// boardreply객체는 ajax로 보낼 때 content, depth를 담아서 보내므로 set할 필요가 없다.
		// reReply객체는 새로 new 한 것 이므로 모든 요소에 대해 set해야함.
		
		board.setReplyCount(board.getReplyCount() + 1); //댓글갯수추가
		boardservice.dataSave(board); 

		List<BoardReply> boardreplylist = getListByBoard(board);	// 추가된 댓글 리스트를 뿌림.
		
		return new ResponseEntity<>(boardreplylist, HttpStatus.CREATED);
	}
	
	private List<BoardReply> getListByBoard(Board board) throws RuntimeException {
		return boardreplyservice.allReplyList(board.getBoardNo());
	}
	
	
	@Transactional
	@PostMapping("/update/{boardNo}")
	public ResponseEntity<List<BoardReply>> updateReply(@PathVariable("boardNo") int boardNo, @RequestBody BoardReply boardreply, Model model) {
		
		System.out.println("--댓글수정--");
		BoardReply updatedBoardReply = boardreplyservice.findBoardRelpy(boardreply.getReplyNo()); // 수정하려는 댓글 객체를 찾는다.
		System.out.println("댓글번호: " + updatedBoardReply.getReplyNo());
		System.out.println("댓글깊이: " + updatedBoardReply.getDepth());
		
		updatedBoardReply.setReplyDate(new Date());	// 댓글시간 초기화
		updatedBoardReply.setReplyContent(boardreply.getReplyContent()); //새로운 내용으로 수정
		boardreplyservice.dataSave(updatedBoardReply); // 재저장.	
			
		Board board = boardservice.findBoard(boardNo);	// 수정하려는 댓글의 board객체를 찾는다.	
		List<BoardReply> boardreplylist = getListByBoard(board);	// 추가된 댓글 리스트를 뿌림.	
		
			return new ResponseEntity<>(boardreplylist, HttpStatus.CREATED);
	}
	
	
	@Transactional
	@PostMapping("/delete/{boardNo}")
	public ResponseEntity<List<BoardReply>> deleteReply(@PathVariable("boardNo") int boardNo, @RequestBody BoardReply boardreply, Model model) {
		
		System.out.println("--댓글삭제--");
		System.out.println("댓글번호: " + boardreply.getReplyNo());
		System.out.println("댓글깊이: " + boardreply.getDepth());
		
		switch(boardreply.getDepth()) {
		case 0: boardreplyservice.deleteParentReply(boardreply.getReplyNo());
		break;
		case 1: boardreplyservice.deleteBoardReply(boardreply.getReplyNo());
		break;
		}
					
		Board board = boardservice.findBoard(boardNo);	// 수정하려는 댓글의 board객체를 찾는다.	
		List<BoardReply> boardreplylist = getListByBoard(board);	// 추가된 댓글 리스트를 뿌림.	
		
			return new ResponseEntity<>(boardreplylist, HttpStatus.CREATED);
	}
	
	 
	
}
