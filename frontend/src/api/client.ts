import axios, { InternalAxiosRequestConfig } from "axios";

export const client = axios.create({
  baseURL: "http://13.125.242.100:8080",
  headers: {
    "Content-Type": "application/json",
  },
});

client.interceptors.request.use((config: InternalAxiosRequestConfig) => {
  const token = localStorage.getItem('accessToken')
  if(!token) {
    config.headers!.Authorization = null;
  } else {
    const accessToken = 'Bearer ' + token;
    config.headers!.Authorization = accessToken
  }
  return config;
});

client.interceptors.response.use(
  function (response) {
    return response;
  },
  async function (error) {
    const response = error;
    const originalRequest = error.config;
    const token = localStorage.getItem('accessToken');
    if (response.code === "order.access-token-expired") {
      const data = await client
        .post(
          'http://13.125.242.100:8080/auth/refresh',
          JSON.stringify({ oldAccessToken: token }),
          {
            withCredentials: true,
          },
        )
        .catch((e) => {
          window.location.replace('/login');
        });
      localStorage.setItem('accessToken', data!.data);
      const accessToken = 'Bearer ' + data!.data;
      client.defaults.headers.Authorization = accessToken;
      return client(originalRequest);
    } else if (response.code === "order.no-valid-token") {
      window.location.replace('/login');
    }
    return Promise.reject(error);
  },
);