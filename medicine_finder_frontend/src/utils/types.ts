import { USER_TYPES } from "./enum";

export type AuthContextData = {
  authenticatedUser?: IAuthenticatedUser;
  login: (token: string) => void;
  logout: () => void;
  setUser: (user: IPharmaUser | ICustomerUser) => void;
};

export interface ResponseBody<T> {
  message: string;
  data: T;
  error: any;
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
}
