package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Board;
import com.example.demo.repository.BoardRepository;

@Service("BoardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardRepository boardrepository;
	
	@Override
	public List<Board> allBoardList() {
		// TODO Auto-generated method stub
		return boardrepository.findAll();
	}

	@Override
	public Board findBoard(int boardNo) {
		// TODO Auto-generated method stub
		return boardrepository.findById(boardNo).get();
	}

	@Override
	public void dataSave(Board board) {
		// TODO Auto-generated method stub
		boardrepository.save(board);
	}

	@Override
	public void deleteBoard(int boardNo) {
		// TODO Auto-generated method stub
		boardrepository.deleteById(boardNo);
	}

}
