package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ImageResponseDTO;
import com.example.demo.service.ImageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class ImageController {

	private final ImageService imageService;

	@PostMapping("/upload")
	public ResponseEntity<ImageResponseDTO> upload(@RequestParam("file") MultipartFile file,
			@RequestParam String targetType, @RequestParam Long targetId, @RequestParam boolean isThumbnail) {
		return ResponseEntity.ok(imageService.uploadImage(file, targetType, targetId, isThumbnail));
	}
}
