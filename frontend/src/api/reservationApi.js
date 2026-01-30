import jwtAxios from "../util/jwtAxios";

// -----인증 필요한 API-----

//슬롯의 예약 조회 (팝업 담당자)
export const getReserveBySlot = async (id) => {
  try {
    const response = await jwtAxios.get(`/api/reservation/slot/${id}`);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

//예약 생성
export const createReserve = async (requestDTO) => {
  try {
    const response = await jwtAxios.post(`/api/reservation/`, requestDTO);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

//예약 수정
export const editReserve = async (id, requestDTO) => {
  try {
    const response = await jwtAxios.patch(`/api/reservation/${id}`, requestDTO);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

//예약 취소
export const cancelReserve = async (id) => {
  try {
    const response = await jwtAxios.delete(`/api/reservation/${id}`);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

//예약 조회
export const getReserve = async (id) => {
  try {
    const response = await jwtAxios.get(`/api/reservation/${id}`);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};
