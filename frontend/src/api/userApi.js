import axios from "axios";
import { API_SERVER_HOST } from "../api/apiConfig";
import jwtAxios from "../util/jwtAxios";

const host = `${API_SERVER_HOST}/api/user`;

// -------인증 필요 없는 API--------

//회원 가입
export const signup = async (userDTO) => {
  try {
    const response = await axios.post(`${host}/signup`, userDTO);
    return response.data;
  } catch (error) {
    console.error("회원가입 중 오류가 발생했습니다: ", error);
    throw error;
  }
};

// -------인증 필요한 API--------

//회원 조회
export const getUser = async (id) => {
  try {
    const response = await jwtAxios.get(`/api/user/${id}`);
    return response.data;
  } catch (error) {
    console.error("회원조회 중 오류가 발생했습니다: ", error);
    throw error;
  }
};

//회원 수정
export const updateUser = async (id, userDTO) => {
  try {
    const response = await jwtAxios.patch(`/api/user/${id}`, userDTO);
    return response.data;
  } catch (error) {
    console.error("회원정보 수정 중 오류가 발생했습니다:", error);
    throw error;
  }
};

//회원 탈퇴
export const deleteUser = async (id) => {
  try {
    const response = await jwtAxios.delete(`/api/user/${id}`);
    return response.data;
  } catch (error) {
    console.error("회원탈퇴 중 오류가 발생했습니다: ", error);
    throw error;
  }
};

//전체 회원 조회
export const getAllUsers = async () => {
  try {
    const response = await jwtAxios.get(`/api/user/list`);
    return response.data;
  } catch (error) {
    console.error("전체 회원 조회 중 오류가 발생했습니다: ", error);
    throw error;
  }
};
