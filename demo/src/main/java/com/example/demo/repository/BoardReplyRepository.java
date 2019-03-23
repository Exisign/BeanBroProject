package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.BoardReply;

public interface BoardReplyRepository extends JpaRepository<BoardReply, Integer> {
	
	@Query(value = "select * from board_reply where board_no = :board_no order by parent_id, depth, reply_date", nativeQuery = true)
	List <BoardReply> allReplyList(int board_no);
	
	@Query(value = "select count(*) from board_reply where board_no = :board_no", nativeQuery = true)
	int countReply(int board_no);
	
	@Modifying
	@Transactional
	@Query(value = "delete from board_reply where parent_id = :parent_id", nativeQuery = true)
	void deleteParentReply(int parent_id);
	
}
