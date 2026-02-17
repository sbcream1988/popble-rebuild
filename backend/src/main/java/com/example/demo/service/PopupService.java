package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.PopupCardDTO;
import com.example.demo.dto.PopupCreateRequestDTO;
import com.example.demo.dto.PopupResponseDTO;
import com.example.demo.dto.PopupUpdateRequestDTO;

public interface PopupService {

	//팝업 등록
	PopupResponseDTO createPopup(PopupCreateRequestDTO popupCreateRequestDTO,Long userId, List<MultipartFile> files);
	
	//팝업 수정
	PopupResponseDTO updatePopup(Long popupId,PopupUpdateRequestDTO popupUpdateRequestDTO, Long userId);
	
	//팝업 삭제
	void deletePopup(Long popupId, Long userId);
	
	//팝업 조회
	PopupResponseDTO getPopup(Long popupId);
	
	//카드 조회?
	List<PopupCardDTO> getPopupCards();
	
}
