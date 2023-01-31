import { QueryFunctionContext } from "react-query/types/core";
import AxiosClient from "../config/axios.config";
import { IPharmaUser, IProduct, ResponseBody } from "../utils/types";

export const fetchPharmaProducts = async ({ queryKey }: QueryFunctionContext<[string, {id: string | undefined}]>) => {
  const [_key, {id} ] = queryKey;
  return AxiosClient.get<ResponseBody<IProduct[]>>(`/product/pharma/${id}`);
};
