import axios from "axios";
import { API_SERVER_HOST } from "./apiConfig";
import jwtAxios from "../util/jwtAxios";

const host = `${API_SERVER_HOST}/api/board/free`;

// ----- 인증 필요한 API -----

// 글 등록
export const createBoard = async (boardDTO) => {
  try {
    const response = await jwtAxios.post(`/api/board/free`, boardDTO);
    return response.data;
  } catch (e) {
    console.error("게시물 등록에 실패하였습니다", e);
    throw e;
  }
};

// 글 수정
export const editBoard = async (id, boardDTO) => {
  try {
    const response = await jwtAxios.patch(`/api/board/free/${id}`, boardDTO);
    return response.data;
  } catch (error) {
    console.error("게시물 수정 중 에러가 발생했습니다", error);
    throw error;
  }
};
// 글 삭제
export const deleteBoard = async (id) => {
  try {
    const response = await jwtAxios.delete(`/api/board/free/${id}`);
    return response.data;
  } catch (error) {
    console.error("게시물 삭제 중 에러가 발생했습니다", error);
    throw error;
  }
};

// ----- 인증 필요 없는 API -----(조회는 비회원도 가능)

// 목록 조회
export const getList = async (page, size) => {
  try {
    const response = await axios.get(`${host}/list`, {
      params: { page, size },
    });
    return response.data;
  } catch (e) {
    console.error("게시판 목록을 불러오는 중 오류가 발생했습니다", e);
    throw e;
  }
};

// 글 조회
export const getBoard = async (id) => {
  try {
    const response = await axios.get(`${host}/${id}`);
    return response.data;
  } catch (error) {
    console.error("게시물을 불러오는 중 오류가 발생했습니다", error);
    throw error;
  }
};
