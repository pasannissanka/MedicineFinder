import React, {
  createContext,
  useCallback,
  useContext,
  useEffect,
  useState,
} from "react";
import { meAPI } from "../api/login.api";
import {
  AuthContextData,
  IAuthenticatedUser,
  ICustomerUser,
  IPharmaUser,
} from "../utils/types";

export const AuthContext = createContext<AuthContextData>({
  login: () => {},
  logout: () => {},
  authenticatedUser: undefined,
  setUser: () => {},
});

export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
  const [authenticatedUser, setAuthenticatedUser] =
    useState<IAuthenticatedUser>();


  const login = useCallback(async (token: string) => {
    localStorage.setItem("auth_token", token);
    try {
      const data = await meAPI();
      if (data && data.data) {
        setAuthenticatedUser({
          user: data.data,
          token,
        });
      }
    } catch (error) {
      setAuthenticatedUser(undefined);
      localStorage.removeItem("auth_token");
    }
  }, []);

  const setUser = useCallback((user: IPharmaUser | ICustomerUser) => {
    const token = localStorage.getItem("auth_token");
    if (token) {
      setAuthenticatedUser({
        token,
        user,
      });
    }
  }, []);

  const logout = useCallback(() => {
    setAuthenticatedUser(undefined);
    localStorage.removeItem("auth_token");
  }, []);

  return (
    <AuthContext.Provider
      value={{
        authenticatedUser,
        login,
        logout,
        setUser,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};
