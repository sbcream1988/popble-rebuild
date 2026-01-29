//로그인/로그아웃 API

import axios from "axios";
import { API_SERVER_HOST } from "./apiConfig";
import { setAccessToken, clearAccessToken } from "../util/tokenStore";

//로그인
export const login = async (email, password) => {
  const result = await axios.post(
    `${API_SERVER_HOST}/api/auth/login`,
    { email, password },
    { withCredentials: true },
  );
  setAccessToken(result.data.accessToken);
  return result.data.user;
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
