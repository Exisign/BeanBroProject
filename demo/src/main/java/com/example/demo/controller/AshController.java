package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Board;
import com.example.demo.domain.Member;
import com.example.demo.repository.BoardRepository;

@Controller
@RequestMapping("/ash")
public class AshController {

	@Autowired
	BoardRepository boardRepository;
	
	//게시판 리스트 출력
	@RequestMapping("/ashBoard")
	public String ashList(Model model){
		List<Board> board = boardRepository.findAll();
		model.addAttribute("list",board);
		return "ash/ashBoard";
	}
	//글읽기폼으로 들어가기
	@RequestMapping("/readForm")
	public String readForm(Model model, Board board) {
		model.addAttribute("read", boardRepository.findById(board.getBoardNo()).get());
		
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
		Board preBoard = boardRepository.findById(board.getBoardNo()).get();
		board.setMember(preBoard.getMember());
		System.out.println("보드넘버:"+board.getBoardNo()); //ok
		System.out.println("바뀐컨텐츠:"+board.getContent()); //ok
		boardRepository.save(board);
		
		System.out.println("수정된보드:"+board); //nok
		//레파지토리 세이브로 DB에 등록
		//save()호출시, 내부에서 엔티티매니져가 해당 식별키 가진 엔티티 존재 확인. 있으면->update / 없으면->insert 
		
		return "redirect:/ash/ashBoard";
	}
	
	//삭제하기
	@RequestMapping("/delete")
	public String delete(Board board) {
		boardRepository.deleteById(board.getBoardNo());
		
		return "redirect:/ash/ashBoard";
	}
}
