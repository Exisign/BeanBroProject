package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.example.demo.domain.Board;
import com.example.demo.domain.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface BoardRepository extends JpaRepository<Board, Integer>,QuerydslPredicateExecutor<Board> {


	@Query(value = "select * from board order by write_date", nativeQuery = true)
	List <Board> allBoardListOrdWD();

	/*
	 * 
	 * #### SAMPLE #####
	@Query(value = "select member_id from member where member_sq = 1", nativeQuery = true)
	Member search_member();
	
	Member findByMemberId(); 
	 
	 *
	 */
	
	//검색에 필요한 타입정보와 키워드를 이용해서 적당한 쿼리를 생성
	public default Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
			
		QBoard board = QBoard.board;
			
		//type if ~ else
			
		//boardNo > 0
		builder.and(board.boardNo.gt(0));
			
		if(type==null) {
			return builder;
		}
			
		switch(type) {
		case "t":
			builder.and(board.title.like("%" + keyword + "%"));
			break;
		case "c":
			builder.and(board.content.like("%" + keyword + "%"));
			break;
		case "w":
			builder.and(board.member.memberId.like("%" + keyword + "%"));
			break;
		}
		return builder;
	}
}
