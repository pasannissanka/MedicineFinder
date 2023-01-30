import React, { useContext, useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import MenuComponent from "../Menu/MenuComponent";
import { Menu, Transition } from "@headlessui/react";
import { AuthContext } from "../../context/AuthContext";
import { USER_TYPES } from "../../utils/enum";
import { ICustomerUser, IPharmaUser } from "../../utils/types";

const Navbar = () => {
  const { logout, authenticatedUser } = useContext(AuthContext);
  const [name, setName] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    if (authenticatedUser?.user.user.role === USER_TYPES.CUSTOMER) {
      const user = authenticatedUser?.user as ICustomerUser;
      setName(`${user.firstName} ${user.lastName}`);
    } else if (authenticatedUser?.user.user.role === USER_TYPES.PHARMA) {
      const user = authenticatedUser?.user as IPharmaUser;
      setName(user.name);
    }
    return () => {
      setName("");
    };
  }, [authenticatedUser]);

  return (
    <div className="w-screen h-12 px-8 shadow-sm bg-gray-100 flex items-center border-b border-gray-200 relative">
      <Link to={"/"}>Medicine Finder</Link>
      {authenticatedUser?.user && (
        <div className="flex absolute right-0 mr-8">
          <MenuComponent
            type="Rounded"
            trigger={
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                strokeWidth={1.5}
                stroke="currentColor"
                className="w-6 h-6"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  d="M17.982 18.725A7.488 7.488 0 0012 15.75a7.488 7.488 0 00-5.982 2.975m11.963 0a9 9 0 10-11.963 0m11.963 0A8.966 8.966 0 0112 21a8.966 8.966 0 01-5.982-2.275M15 9.75a3 3 0 11-6 0 3 3 0 016 0z"
                />
              </svg>
            }
          >
            <div>
              <Menu.Item disabled>
                {({ active }) => (
                  <span className="flex w-full items-center rounded-md px-2 py-2 text-sm">
                    {name}
                  </span>
                )}
              </Menu.Item>

              <Menu.Item>
                {({ active }) => (
                  <button
                    onClick={() => {
                      logout();
                      navigate("/auth")
                    }}
                    className={`${
                      active ? "bg-violet-500 text-white" : "text-gray-900"
                    } group flex w-full items-center rounded-md px-2 py-2 text-sm`}
                  >
                    Logout
                  </button>
                )}
              </Menu.Item>
            </div>
          </MenuComponent>
        </div>
      )}
    </div>
  );
};

export default Navbar;
