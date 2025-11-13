package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.ReviewBoard;

public interface ReviewBoardRepository extends JpaRepository<ReviewBoard, Long>{

}
