import axios from "axios";
import { clearAccessToken, setAccessToken } from "../util/tokenStore";
import { store } from "../store";
import { API_SERVER_HOST } from "../api/apiConfig";

const jwtAxios = axios.create({
  baseURL: API_SERVER_HOST,
  //쿠키 자동 포함
  withCredentials: true,
});

jwtAxios.interceptors.request.use((config) => {
  const state = store.getState();
  const token = state.auth.accessToken;

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
        const result = await jwtAxios.post("/api/auth/refresh");

        setAccessToken(result.data.accessToken);

        error.config.headers.Authorization = `Bearer ${result.data.accessToken}`;
        return jwtAxios(error.config);
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
