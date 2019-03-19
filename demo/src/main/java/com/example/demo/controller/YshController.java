package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Board;
import com.example.demo.domain.BoardReply;
import com.example.demo.domain.Member;
import com.example.demo.service.BoardReplySevice;
import com.example.demo.service.BoardService;

@Controller
@RequestMapping("/ysh")
public class YshController {

	@Autowired
	private BoardService boardservice;
	
	@Autowired
	private BoardReplySevice boardreplyservice;
	
	@RequestMapping("/yshBoard")
	public String yshList(Model model){	// 게시판 리스트 출력.
		List <Board> boardlist = boardservice.allBoardList();
		model.addAttribute("boardlist", boardlist);
		return "ysh/yshBoard";
	}
	
	@RequestMapping("/writePosting")
	public String yshWriting(){	// 글쓰기 폼 이동.
		return "ysh/writePosting";
	}
	
	@RequestMapping("/registerPosting")	
	public String yshRegistering(@ModelAttribute Board board){	// 글쓰기. insert
		Member member = new Member();
		
		/*
		 * member.setMemberNo(1); // 임시로 멤버객체삽입하여 글쓴이(writer) 대체. 미연누님께서 member table
		 * 완성시켜주시면 제대로된 member 객체 삽입 board.setMember(member);
		 */
		  boardservice.dataSave(board);
		 
		return "redirect:/ysh/yshBoard";
	}
	
	@RequestMapping("/readPosting")	
	public String yshReading(Model model, @RequestParam int boardNo){	// 쓴글 조회.
		Board board = boardservice.findBoard(boardNo);
		int hit = board.getHit()+1;	// 글 read 시 조회수 ++
		board.setHit(hit);			
		boardservice.dataSave(board);		
		model.addAttribute("board", board);
		
		return "ysh/readPosting";
	}
	
	@RequestMapping("/modifyPosting")	
	public String yshModify(Model model, @RequestParam int boardNo){	// 쓴글 수정창이동..
		Board board = boardservice.findBoard(boardNo);
		model.addAttribute("board", board);
		return "ysh/modifyPosting";
	}
	
	/*
	 * @RequestMapping("/activeModifiedPosting") public String yshModifyAction(Model
	 * model, @RequestParam int boardNo){ // 쓴글 수정. Board board =
	 * boardservice.findBoard(boardNo); boardservice.dataSave(board);
	 * model.addAttribute("board", board); return
	 * "redirect:/ysh/readPosting?boardNo=" + boardNo; }
	 */
	
	@RequestMapping("/activeModifiedPosting")	
	public String yshModifyAction(Model model, @RequestParam int boardNo, @ModelAttribute Board board){	// 쓴글 수정.	
		Board preBoard = boardservice.findBoard(boardNo);
		board.setMember(preBoard.getMember());			// 이전에 수정한 글과 멤버객체 동일하게
		board.setBoardType(preBoard.getBoardType());	// 이전에 수정한 글과 테이블종류 동일하게
		boardservice.dataSave(board);
		model.addAttribute("board", board);
		return "redirect:/ysh/readPosting?boardNo=" + boardNo;
	}
	
	@RequestMapping("/deletePosting")	
	public String yshDelete(Model model, @RequestParam int boardNo){	// 쓴글 수정.
		boardservice.deleteBoard(boardNo);	// 글 삭제.
		return "redirect:/ysh/yshBoard";
	}
	
	
	
}
