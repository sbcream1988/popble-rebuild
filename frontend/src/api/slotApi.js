import axios from "axios";
import { API_SERVER_HOST } from "./apiConfig";
import jwtAxios from "../util/jwtAxios";

const host = `${API_SERVER_HOST}/api`;

//---------인증 필요 없는 API-----------

//슬롯 조회
export const getSlot = async (id) => {
  try {
    const response = await axios.get(`${host}/slots/${id}`);
    return response.data;
  } catch (error) {
    console.log(error);
    throw error;
  }
};

// 팝업별 슬롯 조회
export const getSlotsByPopup = async (id) => {
  try {
    const response = await axios.get(`${host}/popup/${id}/slots`);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

// 팝업별 슬롯 날짜조회
export const getSlotsByPopupAndDate = async (id, date) => {
  try {
    const response = await axios.get(`${host}/popup/${id}/slots/date`, {
      params: { date },
    });
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

//----------인증 필요한 API-------------

//슬롯 생성
export const createSlot = async (id, requestDTO) => {
  try {
    const response = await jwtAxios.post(`/api/popup/${id}/slots`, requestDTO);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

//슬롯 수정
export const editSlot = async (id, requestDTO) => {
  try {
    const response = await jwtAxios.patch(`/api/slots/${id}`, requestDTO);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

//슬롯 제거
export const deleteSlot = async (id) => {
  try {
    const response = await jwtAxios.delete(`/api/slots/${id}`);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};
