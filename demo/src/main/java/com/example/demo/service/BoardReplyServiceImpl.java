package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.BoardReply;
import com.example.demo.repository.BoardReplyRepository;

@Service("BoardReplyService")
public class BoardReplyServiceImpl implements BoardReplySevice {

	@Autowired
	BoardReplyRepository boardreplyrepository;
	
	@Override
	public List<BoardReply> allReplyList(int BoardNo) {
		// TODO Auto-generated method stub
		return boardreplyrepository.allReplyList(BoardNo);
	}

	@Override
	public BoardReply findBoardRelpy(int replyNo) {
		// TODO Auto-generated method stub
		return boardreplyrepository.findById(replyNo).get();
	}

	@Override
	public void dataSave(BoardReply boardRelpy) {
		// TODO Auto-generated method stub
		boardreplyrepository.save(boardRelpy);
	}

	@Override
	public void deleteBoardReply(int replyNo) {
		// TODO Auto-generated method stub
		boardreplyrepository.deleteById(replyNo);
	}

	@Override
	public int countReply(int boardNo) {
		// TODO Auto-generated method stub
		return boardreplyrepository.countReply(boardNo);
	}

	@Override
	public void deleteParentReply(int parent_id) {
		boardreplyrepository.deleteParentReply(parent_id);
		
	}

}
