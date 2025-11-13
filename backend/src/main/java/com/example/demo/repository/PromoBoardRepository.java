package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.PromoBoard;

public interface PromoBoardRepository extends JpaRepository<PromoBoard, Long>{

}
