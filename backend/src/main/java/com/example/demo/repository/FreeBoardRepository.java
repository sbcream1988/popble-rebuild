package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.FreeBoard;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long>{

	Page<FreeBoard> findAll(Pageable pageable);
}
