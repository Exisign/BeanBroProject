package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Board;
import com.example.demo.domain.Member;
import com.example.demo.repository.BoardRepository;
import com.example.demo.vo.PageMaker;
import com.example.demo.vo.PageVO;

@Controller
@RequestMapping("/ash")
public class AshController {

	private int i = 0;
	
	@Autowired
	BoardRepository boardRepository;
	
	//게시판 리스트 출력
	@RequestMapping("/ashBoard")
	public String ashList(Model model,@ModelAttribute("pageVO") PageVO vo){
		Pageable page = vo.makePageable(0, "boardNo");
		
		Page<Board> result = boardRepository.findAll(boardRepository.makePredicate(vo.getType(), vo.getKeyword()),page);
		
		model.addAttribute("result", new PageMaker(result));
		
		return "ash/ashBoard";
	}
	
	//글읽기폼으로 들어가기
	@GetMapping("/readForm")
	public String readForm(Model model, @RequestParam int boardNo, @ModelAttribute("pageVO") PageVO vo) {
		
		Board newBoard = boardRepository.findById(boardNo).get();
		newBoard.setHit(newBoard.getHit()+1);
		boardRepository.save(newBoard);
		model.addAttribute("read", newBoard);
		
		return "ash/readForm";
	}
	
	//글쓰기폼으로 들어가기
	@RequestMapping("/writeForm")
	public String writeForm(Model model){
		
		return "ash/WriteForm";
	}
	
	//글쓰기폼으로 들어가기
	@RequestMapping("/writeComplete")
	public String writeComplete(Model model, Board board, Member member){
		//임시로 미연님 멤버데이터 셋
		member.setMemberNo(1);
		//셋한 멤버변수를 보드에 셋
		board.setMember(member);
		//레파지토리 세이브로 DB에 등록
		//save()호출시, 내부에서 엔티티매니져가 해당 식별키 가진 엔티티 존재 확인. 있으면->update / 없으면->insert 
		boardRepository.save(board);
		
		return "redirect:/ash/ashBoard";
	}
	
	//목록으로 돌아가기
	@RequestMapping("/back")
	public String back() {
		return "redirect:/ash/ashBoard";
	}
	
	//수정할 수 있는 폼으로 들어가기
	@RequestMapping("/updateForm")
	public String updateForm(Model model, Board board) {
		model.addAttribute("read", boardRepository.findById(board.getBoardNo()).get());
		return "ash/updateForm";
	}
	
	//수정완료하기
	@RequestMapping("/updateComplete")
	public String updateComplete(Model model, Member member, Board board) {
		Board updateBoard = boardRepository.findById(board.getBoardNo()).get(); //보드넘버에 맞는 데이터값들을 가져온다.
		board.setMember(updateBoard.getMember()); //가져온 보드넘버값의 멤버를 보드에 셋한다.
		//레파지토리 세이브로 DB에 등록
		//save()호출시, 내부에서 엔티티매니져가 해당 식별키 가진 엔티티 존재 확인. 있으면->update / 없으면->insert 
		boardRepository.save(board);
		
		return "redirect:/ash/ashBoard";
	}
	
	//삭제하기
	@RequestMapping("/delete")
	public String delete(Board board) {
		boardRepository.deleteById(board.getBoardNo());
		
		return "redirect:/ash/ashBoard";
	}
}