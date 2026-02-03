//로그인/로그아웃 API

import axios from "axios";
import { API_SERVER_HOST } from "./apiConfig";
import { setAccessToken, clearAccessToken } from "../util/tokenStore";
import jwtAxios from "../util/jwtAxios";

//로그인
export const login = async (email, password) => {
  const result = await jwtAxios.post(
    `${API_SERVER_HOST}/api/auth/login`,
    { email, password },
    { withCredentials: true },
  );
  const { accessToken, userId, email: userEmail, nickname, role } = result.data;
  setAccessToken(accessToken);
  return { accessToken, userId, email: userEmail, nickname, role };
};

//로그아웃
export const logout = async () => {
  await axios.post(
    `${API_SERVER_HOST}/api/auth/logout`,
    {},
    { withCredentials: true },
  );
  clearAccessToken();
};
