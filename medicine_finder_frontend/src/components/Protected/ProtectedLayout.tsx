import React, { useContext, useEffect } from "react";
import { AuthContext } from "../../context/AuthContext";
import { useNavigate, Outlet, Navigate, useLoaderData } from "react-router-dom";
import { ICustomerUser, IPharmaUser } from "../../utils/types";

const ProtectedLayout = () => {
  const data = useLoaderData() as ICustomerUser | IPharmaUser;
  const { setUser } = useContext(AuthContext);
  const navigate = useNavigate();

  useEffect(() => {
    if (data) {
      setUser(data);
    } else {
      navigate("/auth/login");
    }
  }, [data]);

  return <Outlet />;
};

export default ProtectedLayout;
