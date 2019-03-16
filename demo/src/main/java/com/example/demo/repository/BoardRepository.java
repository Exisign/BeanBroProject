package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

	/*
	 * 
	 * #### SAMPLE #####
	@Query(value = "select member_id from member where member_sq = 1", nativeQuery = true)
	Member search_member();
	
	Member findByMemberId(); 
	 
	 *
	 */
	
}
