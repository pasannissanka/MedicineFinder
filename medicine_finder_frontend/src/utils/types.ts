import { USER_TYPES } from "./enum";

export type AuthContextData = {
  authenticatedUser?: IAuthenticatedUser;
  login: (token: string) => void;
  logout: () => void;
  setUser: (user: IPharmaUser | ICustomerUser) => void;
};

export interface ResponseBody<T> {
  status: string;
  message: string;
  data: T;
  errors?: any;
  metadata?: any;
}

export interface IAuthenticatedUser {
  user: ICustomerUser | IPharmaUser;
  token: string;
}

export interface IAuthUser {
  id: string;
  email: string;
  role: USER_TYPES;
  verified: boolean;
}

export interface ICustomerUser {
  id: string;
  firstName: string;
  lastName: string;
  user: IAuthUser;
}

export interface IPharmaUser {
  id: string;
  user: IAuthUser;
  details: string;
  name: string;
}

export interface ICreateCustomer {
  email: string;
  password: string;
  firstName: string;
  lastName: string;
}

export interface ICreatePharma {
  email: string;
  password: string;
  details: string;
  address: string;
  name: string;
  lat: number;
  lng: number;
}

export interface IProduct {
  id: string;
  brandName: string;
  genericName: string;
  price: string;
  available: boolean;
  description: string;
  pharma?: IPharmaUser;
}

export interface IProductAdd {
  brandName: string;
  genericName: string;
  price: string;
  available: boolean;
  description: string;
}
