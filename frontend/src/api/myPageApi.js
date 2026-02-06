import jwtAxios from "../util/jwtAxios";

//내 정보 조회
export const getMyInfo = async () => {
  try {
    const response = await jwtAxios.get(`/api/user/me`);
    console.log(response.data);
    return response.data;
  } catch (e) {
    console.error("내 정보 조회에 실패했습니다", e);
    throw e;
  }
};

// 내 정보 수정
export const updateMyInfo = async (myPageRequestDTO) => {
  try {
    const response = await jwtAxios.put(`/api/user/me`, myPageRequestDTO);
    console.log(response.data);
    return response.data;
  } catch (e) {
    console.error("내 정보 수정에 실패했습니다", e);
    throw e;
  }
};

// 비밀번호 수정
export const changePassword = async (changeDTO) => {
  try {
    const response = await jwtAxios.put(`/api/user/me/password`, changeDTO);
    console.log(response.data);
    return response.data;
  } catch (e) {
    console.error("비밀번호 수정에 실패했습니다", e);
    throw e;
  }
};

// 회원 탈퇴
export const withdrawMember = async () => {
  try {
    const response = await jwtAxios.delete(`/api/user/me`);
    console.log(response.data);
    return response.data;
  } catch (e) {
    console.error("회원 탈퇴에 실패했습니다", e);
    throw e;
  }
};
