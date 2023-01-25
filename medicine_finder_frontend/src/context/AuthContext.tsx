import React, {
  createContext,
  useCallback,
  useContext,
  useEffect,
  useState,
} from "react";
import { meAPI } from "../api/login.api";
import { AuthContextData, IAuthenticatedUser } from "../utils/types";

export const AuthContext = createContext<AuthContextData>({
  login: () => {},
  logout: () => {},
  authenticatedUser: undefined,
});

export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
  const [authenticatedUser, setAuthenticatedUser] =
    useState<IAuthenticatedUser>();

  useEffect(() => {
    const token = localStorage.getItem("auth_token");
    if (token) {
      login(token);
    }

    return () => {
      setAuthenticatedUser(undefined);
    };
  }, []);

  const login = useCallback(async (token: string) => {
    localStorage.setItem("auth_token", token);
    const data = await meAPI(token);
    if (data && data.data) {
      setAuthenticatedUser({
        user: data.data,
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
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};
