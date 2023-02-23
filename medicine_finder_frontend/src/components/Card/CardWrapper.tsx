import React from "react";

const CardWrapper = ({ children }: { children: React.ReactNode }) => {
  return (
    <div className="w-full flex flex-col border border-gray-300 px-6 py-4 rounded-md my-3">
      {children}
    </div>
  );
};

export default CardWrapper;
