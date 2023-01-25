import axios from "axios";

const AxiosClient = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
  timeout: 1000,
});

AxiosClient.interceptors.request.use(function (config) {
  const token = localStorage.getItem("auth_token");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  } else {
    config.headers.Authorization = null;
  }
  return config;
});

export default AxiosClient;