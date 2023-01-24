import React from "react";
import { Outlet } from "react-router-dom";

import "../../styles/pattern.css";

const AuthPage = () => {
  return (
    <div className="w-full h-screen bg_pattern bg-cyan-200">
      <div className="flex flex-col items-center justify-center m-auto h-screen">
        <div className="border border-gray-200 shadow-sm rounded-md p-4 w-2/4 bg-white">
        <Outlet />
        </div>
      </div>
    </div>
  );
};

export default AuthPage;
