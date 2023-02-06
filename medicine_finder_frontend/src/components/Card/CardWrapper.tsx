import React from "react";

const CardWrapper = ({ children }: { children: React.ReactNode }) => {
  return (
    <div className="w-full col-span-1 flex flex-col border border-gray-300 px-6 py-4 rounded-md">
      {children}
    </div>
  );
};

export default CardWrapper;
