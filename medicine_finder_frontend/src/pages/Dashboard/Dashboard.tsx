import React, { useContext, useEffect } from "react";
import { AuthContext } from "../../context/AuthContext";
import { USER_TYPES } from "../../utils/enum";
import AdminDashboard from "./AdminDashboard";
import CustomerDashboard from "./CustomerDashboard";

const Dashboard = () => {
  const { authenticatedUser } = useContext(AuthContext);

  if (
    authenticatedUser?.user &&
    authenticatedUser.user.user.role === USER_TYPES.CUSTOMER
  ) {
    return <CustomerDashboard />;
  } else if (
    authenticatedUser?.user &&
    authenticatedUser.user.user.role === USER_TYPES.PHARMA
  ) {
    return <AdminDashboard />;
  }

  return <div>Loading...</div>;
};

export default Dashboard;
