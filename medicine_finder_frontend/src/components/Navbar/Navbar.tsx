import React from "react";
import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <div className="w-screen h-12 px-8 shadow-md bg-blue-700 flex items-center text-white">
      <Link to={"/"}>Medicine Finder</Link>

    </div>
  );
};

export default Navbar;
