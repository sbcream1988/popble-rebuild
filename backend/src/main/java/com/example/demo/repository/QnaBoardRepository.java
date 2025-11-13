package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.QnaBoard;

public interface QnaBoardRepository extends JpaRepository<QnaBoard, Long>{

}
