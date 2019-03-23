package com.example.demo;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.Board;
import com.example.demo.domain.Member;
import com.example.demo.repository.BoardRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class BoardInsertTest {
	@Autowired
	BoardRepository repo;
	
	@Test
	public void insertBoardDummies() {
		
		IntStream.range(0, 200).forEach(i->{
			Board board = new Board();
			Member member = new Member();
			
			board.setBoardType("ash");
			member.setMemberNo(1);
			board.setMember(member);
			board.setTitle("Sample Board Title"+i);
			board.setContent("Content Sample..." + i + " of Board");
			
			repo.save(board);
		});
	}
}
