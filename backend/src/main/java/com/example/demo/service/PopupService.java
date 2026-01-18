package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.PopupCardDTO;
import com.example.demo.dto.PopupCreateRequestDTO;
import com.example.demo.dto.PopupResponseDTO;
import com.example.demo.dto.PopupUpdateRequestDTO;

public interface PopupService {

	//팝업 등록
	PopupResponseDTO createPopup(PopupCreateRequestDTO popupCreateRequestDTO);
	
	//팝업 수정
	PopupResponseDTO updatePopup(Long popupId,PopupUpdateRequestDTO popupUpdateRequestDTO);
	
	//팝업 삭제
	void deletePopup(Long popupId);
	
	//팝업 조회
	PopupResponseDTO getPopup(Long popupId);
	
	//카드 조회?
	List<PopupCardDTO> getPopupCards();
	
}
