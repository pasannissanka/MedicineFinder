import { QueryFunctionContext } from "react-query/types/core";
import AxiosClient from "../config/axios.config";
import {
  IPharmaUser,
  IProduct,
  IProductAdd,
  ResponseBody,
} from "../utils/types";

export const fetchPharmaProducts = async ({
  queryKey,
}: QueryFunctionContext<[string, { id: string | undefined }]>) => {
  const [_key, { id }] = queryKey;
  return AxiosClient.get<ResponseBody<IProduct[]>>(`/product/pharma/${id}`);
};

export const createProduct = async (payload: IProductAdd) => {
  return AxiosClient.post<ResponseBody<IProduct>>("/product", payload);
};

export const updateProduct = async (payload: {
  data: IProductAdd;
  id: string;
}) => {
  return AxiosClient.post<ResponseBody<IProduct>>(
    `/product/${payload.id}`,
    payload.data
  );
};

export const deleteProduct = async (payload: string) => {
  return AxiosClient.delete<ResponseBody<IProduct>>(`/product/${payload}`);
};
