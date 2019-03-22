package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

	@Query(value = "select * from board order by write_date", nativeQuery = true)
	List <Board> allBoardListOrdWD();
	
}
