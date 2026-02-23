package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Popup;

public interface PopupRepository extends JpaRepository<Popup, Long> {

	List<Popup> findByOwnerId(Long ownerId);
}
