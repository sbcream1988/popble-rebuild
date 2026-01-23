import axios from "axios";
import { API_SERVER_HOST } from "./apiConfig";

const host = `${API_SERVER_HOST}/api/reservations`;

//차후에 비회원이 예약-> axios // 회원이 예약 jwtAxios

//예약 생성
export const createReserve = async (requestDTO) => {
  try {
    const response = await axios.post(`${host}/`, requestDTO);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

//예약 수정
export const editReserve = async (id, requestDTO) => {
  try {
    const response = await axios.patch(`${host}/${id}`, requestDTO);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

//예약 취소
export const cancelReserve = async (id) => {
  try {
    const response = await axios.delete(`${host}/${id}`);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

//예약 조회
export const getReserve = async (id) => {
  try {
    const response = await axios.get(`${host}/${id}`);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

//슬롯의 예약 조회
export const getReserveBySlot = async (id) => {
  try {
    const response = await axios.get(`${host}/slot/${id}`);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};
