import React, { useContext, useEffect } from "react";
import { AuthContext } from "../../context/AuthContext";
import { useNavigate, Outlet, Navigate, useLoaderData } from "react-router-dom";
import { ICustomerUser, IPharmaUser, ResponseBody } from "../../utils/types";

const ProtectedLayout = () => {
  const data = useLoaderData() as ResponseBody<ICustomerUser | IPharmaUser>;
  const { setUser } = useContext(AuthContext);
  const navigate = useNavigate();

  useEffect(() => {
    if (data && data.data) {
      console.log(data);
      setUser(data.data);
    } else {
      navigate("/auth");
    }
  }, [data]);

  return <Outlet />;
};

export default ProtectedLayout;
