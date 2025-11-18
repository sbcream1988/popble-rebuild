import axios from "axios";
import { API_SERVER_HOST } from "./apiConfig";

const host = `${API_SERVER_HOST}/api/board/free`;

// 목록 조회
export const getList = async () => {
  try {
    const response = await axios.get(`${host}/list`);
    return response.data;
  } catch (e) {
    console.error("게시판 목록을 불러오는 중 오류가 발생했습니다", e);
    throw e;
  }
};

// 글 등록
export const create = async (boardDTO) => {
  try {
    const response = await axios.post(`${host}`, boardDTO);
    return response.data;
  } catch (e) {
    console.error("게시물 등록에 실패하였습니다", e);
    throw e;
  }
};

// 글 조회

// 글 수정

// 글 삭제
