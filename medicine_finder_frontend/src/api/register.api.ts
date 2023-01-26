import AxiosClient from "../config/axios.config";
import { ICreateCustomer, ICreatePharma, ICustomerUser, IPharmaUser, ResponseBody } from "../utils/types";

export const registerCustomerAPI = async (payload: ICreateCustomer) => {
  return await AxiosClient.post<ResponseBody<ICustomerUser>>(
    "/auth/public/customer",
    payload
  );
};

export const registerPharmaAPI = async (payload: ICreatePharma) => {
    return await AxiosClient.post<ResponseBody<IPharmaUser>>(
      "/auth/public/pharma",
      payload
    );
  };
