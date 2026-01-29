import axios from "axios";
import {
  getAccessToken,
  clearAccessToken,
  setAccessToken,
} from "../util/tokenStore";

const jwtAxios = axios.create({
  baseURL: "http://localhost:8080",
  //쿠키 자동 포함
  withCredentials: true,
});

jwtAxios.interceptors.request.use((config) => {
  const token = getAccessToken();

  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }

  return config;
});

jwtAxios.interceptors.response.use(
  (result) => result,
  async (error) => {
    if (error.response?.status === 401) {
      try {
        const result = await axios.post(
          "/api/auth/refresh",
          {},
          { baseURL: "http://localhost:8080", withCredentials: true },
        );

        setAccessToken(result.data.accessToken);

        error.config.headers.Authorization = `Bearer ${result.data.accessToken}`;
        return axios(error.config);
      } catch {
        clearAccessToken();
        //로그인 페이지로
        window.location.href = "/login";
      }
    }
    return Promise.reject(error);
  },
);

export default jwtAxios;
