import React from "react";
import { Outlet } from "react-router-dom";

import "../../styles/pattern.css";

const AuthPage = () => {
  return (
    <div className="w-full h-full min-h-screen bg_pattern bg-cyan-200">
      <div className="flex flex-col items-center justify-center min-h-screen m-auto">
        <div className="border border-gray-200 shadow-sm rounded-md p-4 sm:w-2/5 w-full bg-white my-5">
          <Outlet />
        </div>
      </div>
    </div>
  );
};

export default AuthPage;
