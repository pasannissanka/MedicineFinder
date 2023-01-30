import React from "react";

const Button = ({
  children,
  className,
  ...props
}: React.ButtonHTMLAttributes<HTMLButtonElement>) => {
  return (
    <button
      {...props}
      className={`px-4 py-2 border bg-blue-700 text-white rounded-md hover:bg-blue-500 duration-150 ${className}`}
    >
      {children}
    </button>
  );
};

export default Button;
