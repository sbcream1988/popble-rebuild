package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.FreeBoard;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long>{
	@Query("select f from FreeBoard f join fetch f.writer")
	Page<FreeBoard> findAllWithWriter(Pageable pageable);
}
