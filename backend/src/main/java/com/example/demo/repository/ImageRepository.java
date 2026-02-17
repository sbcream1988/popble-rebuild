package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{

	// 특정 게시글(or 팝업)에 속한 모든 이미지 조회
	List<Image> findByTargetTypeAndTargetId(String targetType, Long targetId);
}
