package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.Image;
import com.example.demo.dto.ImageResponseDTO;

public interface ImageService {

	ImageResponseDTO uploadImage(MultipartFile multipartFile, String targetType, Long targetId, boolean isThumbnail);
}
