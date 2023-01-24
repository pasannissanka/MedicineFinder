import React from "react";
import { useRouteError } from "react-router-dom";

const NotFoundPage = () => {
  const error = useRouteError();

  return (
    <div className="flex flex-col gap-1 items-center w-full m-auto">
      <div className="text-3xl font-extrabold text-red-700">404</div>
      <span className="text-lg text-gray-500">Not Found</span>
    </div>
  );
};

export default NotFoundPage;
