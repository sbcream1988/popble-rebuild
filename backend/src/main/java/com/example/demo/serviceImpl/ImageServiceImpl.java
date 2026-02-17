package com.example.demo.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.Image;
import com.example.demo.dto.ImageResponseDTO;
import com.example.demo.mapper.ImageMapper;
import com.example.demo.repository.ImageRepository;
import com.example.demo.service.ImageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

	private final ImageRepository imageRepository;
	
	//로컬 저장 경로
	private final String uploadPath = "C:/demo/uploads/";
	
	@Override
	public ImageResponseDTO uploadImage(MultipartFile file, String targetType, Long targetId, boolean isThumbnail) {
		
		if(file.isEmpty()) {
			throw new RuntimeException("파일이 비어있습니다");
		}
		
		// 디렉토리 생성
		File dir = new File(uploadPath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		String originName = file.getOriginalFilename();
		String uuid = UUID.randomUUID().toString();
		String extension = "";
		if(originName != null && originName.contains(".")) {
			extension = originName.substring(originName.lastIndexOf("."));
		}
		String storedName = uuid + extension;
		String savePath = uploadPath + storedName;
		
		try {
			file.transferTo(new File(savePath));
		} catch (IOException e) {
			throw new RuntimeException("파일 저장 실패",e);
		} 
		
		Image image = Image.builder()
				.originName(originName)
				.storedName(storedName)
				.accessUrl("/images/" + storedName)
				.targetType(targetType)
				.targetId(targetId)
				.isThumbnail(isThumbnail)
				.build();
		
		Image savedImage = imageRepository.save(image);
		
		return ImageMapper.fromEntity(savedImage);
	}
}
