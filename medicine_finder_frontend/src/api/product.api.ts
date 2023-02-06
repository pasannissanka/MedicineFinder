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
  return AxiosClient.get<ResponseBody<IProduct[]>>(`/pharmas/${id}/products`);
};

export const createProduct = async (payload: IProductAdd) => {
  return AxiosClient.post<ResponseBody<IProduct>>("/products", payload);
};

export const updateProduct = async (payload: {
  data: IProductAdd;
  id: string;
}) => {
  return AxiosClient.post<ResponseBody<IProduct>>(
    `/products/${payload.id}`,
    payload.data
  );
};

export const deleteProduct = async (payload: string) => {
  return AxiosClient.delete<ResponseBody<IProduct>>(`/products/${payload}`);
};

export const searchProducts = async ({
  queryKey,
}: QueryFunctionContext<[string, { name: string | undefined }]>) => {
  const [_key, { name }] = queryKey;
  return AxiosClient.get<ResponseBody<IProduct[]>>(
    `/products/search?name=${name}`
  );
};
