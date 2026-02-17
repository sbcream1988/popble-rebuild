//백엔드와 연결
//react -> process.env.REACT_APP_API_BASE_URL
//vite -> import.meta.env.VITE_APP_API_BASE_URL
export const API_SERVER_HOST = import.meta.env.VITE_APP_API_BASE_URL;
console.log("현재 API 주소:", API_SERVER_HOST);
