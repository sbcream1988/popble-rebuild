import axios from "axios";
import { API_SERVER_HOST } from "./apiConfig";
import jwtAxios from "../util/jwtAxios";

// ----- 인증 필요한 API -----

//팝업 등록
export const createPopup = async (requestDTO, images) => {
  const formData = new FormData();

  //Json 부분
  formData.append(
    "data",
    new Blob([JSON.stringify(requestDTO)], {
      type: "application/json",
    }),
  );

  // 이미지 여러개
  if (images && images.length > 0) {
    images.forEach((image) => {
      formData.append("images", image);
    });
  }

  try {
    const response = await jwtAxios.post(`/api/popup/create`, formData);
    return response.data;
  } catch (error) {
    console.error("팝업스토어 등록에 실패했습니다", error);
    throw error;
  }
};

//팝업 수정
export const editPopup = async (id, requestDTO) => {
  try {
    const response = await jwtAxios.patch(`/api/popup/${id}`, requestDTO);
    return response.data;
  } catch (error) {
    console.error("팝업스토어 수정에 실패했습니다", error);
    throw error;
  }
};

//팝업 삭제
export const deletePopup = async (id) => {
  try {
    const response = await jwtAxios.delete(`/api/popup/${id}`);
    return response.data;
  } catch (error) {
    console.error("팝업스토어 삭제에 실패했습니다", error);
    throw error;
  }
};

// 내 팝업목록 조회
export const getMyPopups = async () => {
  try {
    const response = await jwtAxios.get(`/api/popup/my`);
    return response.data;
  } catch (error) {
    console.error("팝업 목록 조회 실패", error);
    throw error;
  }
};

// ----- 인증 필요 없는 API ----- (비회원도 조회는 가능해야함)

//팝업 조회
export const getPopup = async (id) => {
  try {
    const response = await axios.get(`${API_SERVER_HOST}/api/popup/${id}`);
    return response.data;
  } catch (error) {
    console.error("팝업스토어 조회에 실패했습니다", error);
    throw error;
  }
};

//팝업 리스트 조회
export const getPopupList = async () => {
  try {
    const response = await axios.get(`${API_SERVER_HOST}/api/popup/list`);
    return response.data;
  } catch (error) {
    console.error("팝업스토어 리스트 조회에 실패했습니다", error);
    throw error;
  }
};
