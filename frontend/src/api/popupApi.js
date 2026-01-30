import axios from "axios";
import { API_SERVER_HOST } from "./apiConfig";
import jwtAxios from "../util/jwtAxios";

const host = `${API_SERVER_HOST}/api/popup`;

// ----- 인증 필요한 API -----

//팝업 등록
export const createPopup = async (requestDTO) => {
  try {
    const response = await jwtAxios.post(`/api/popup/create`, requestDTO);
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

// ----- 인증 필요 없는 API ----- (비회원도 조회는 가능해야함)

//팝업 조회
export const getPopup = async (id) => {
  try {
    const response = await axios.get(`${host}/${id}`);
    return response.data;
  } catch (error) {
    console.error("팝업스토어 조회에 실패했습니다", error);
    throw error;
  }
};

//팝업 리스트 조회
export const getPopupList = async () => {
  try {
    const response = await axios.get(`${host}/list`);
    return response.data;
  } catch (error) {
    console.error("팝업스토어 리스트 조회에 실패했습니다", error);
    throw error;
  }
};
