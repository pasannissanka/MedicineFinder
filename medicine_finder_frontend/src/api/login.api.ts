import { LoginReqBody } from "../components/Login/Login";
import AxiosClient from "../config/axios.config";
import { ICustomerUser, IPharmaUser, ResponseBody } from "../utils/types";

export const loginAPI = async (payload: LoginReqBody) => {
  const response = await AxiosClient.post<ResponseBody<{ token: string }>>(
    "/auth/public/authenticate",
    payload
  );

  if (response.status === 200) {
    return response.data;
  }
  return null;
};

export const meAPI = async () => {
  const response = await AxiosClient.get<
    ResponseBody<ICustomerUser | IPharmaUser>
  >("/auth");
  return response.data;
};

export const verifyAccountApi = async (payload: { token: string }) => {
  const response = await AxiosClient.post<ResponseBody<boolean>>(
    `/auth/public/verify?token=${payload.token}`,
    {}
  );
  return response.data;
};
