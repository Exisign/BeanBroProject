package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.BoardReply;

public interface BoardReplySevice {

	public List<BoardReply> allReplyList(int boardNo);
	public BoardReply findBoardRelpy(int replyNo);
	public void dataSave(BoardReply boardRelpy);
	public void deleteBoardReply(int replyNo);
	public int countReply(int boardNo);
	
}
