import AxiosClient from "../config/axios.config";
import { IPharmaUser, ResponseBody } from "../utils/types";

export const getAllPharma = async () => {
  return AxiosClient.get<ResponseBody<IPharmaUser[]>>("/pharmas");
};
