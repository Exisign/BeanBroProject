package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Board;

public interface BoardService {

	public List<Board> allBoardList();
	public List<Board> allBoardListOrdWD();
	public Board findBoard(int boardNo);
	public void dataSave(Board board);
	public void deleteBoard(int boardNo);
	
	
}
