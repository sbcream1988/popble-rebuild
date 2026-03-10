//백엔드와 연결
//react -> process.env.REACT_APP_API_BASE_URL

import axios from "axios";

//vite -> import.meta.env.VITE_APP_API_BASE_URL
export const API_SERVER_HOST = import.meta.env.VITE_APP_API_BASE_URL;
console.log("현재 API 주소:", API_SERVER_HOST);

const api = axios.create({
  baseURL: API_SERVER_HOST,
  withCredentials: true,
});

api.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    if (error.response && error.response.data) {
      const { status, code, message } = error.response.data;
      console.error(`에러코드:${code}, 메세지:${message}`);

      switch (status) {
        case 401:
          alert("세션만료. 다시 로그인해주세요");
          window.location.href = "/login";
          break;
        case 403:
          alert("권한이 없습니다");
          break;
        case 404:
          alert(message || "존재하지 않는 페이지입니다");
          break;
        case 500:
          alert("서버오류가 발생했습니다. 잠시후 다시 시도해주세요");
          break;
        default:
          alert(message || "알 수 없는 오류 발생");
      }
    } else {
      alert("서버와 연결 할 수 없습니다");
    }
    return Promise.reject(error);
  },
);

export default api;
