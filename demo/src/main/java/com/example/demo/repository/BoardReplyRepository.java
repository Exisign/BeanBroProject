package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.BoardReply;

public interface BoardReplyRepository extends JpaRepository<BoardReply, Integer> {
	
	@Query(value = "select * from board_reply where board_no = :board_no order by depth, reply_date", nativeQuery = true)
	List <BoardReply> allReplyList(int board_no);
	
	@Query(value = "select count(*) from board_reply where board_no = :board_no", nativeQuery = true)
	int countReply(int board_no);
	
}
