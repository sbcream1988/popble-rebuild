// 현재 살아있는 accessToken을 저장하는 메모리 공간
// 단순히 주머니 역할

let accessToken = null;

export const setAccessToken = (token) => {
  accessToken = token;
};

export const getAccessToken = () => accessToken;

export const clearAccessToken = () => {
  accessToken = null;
};
