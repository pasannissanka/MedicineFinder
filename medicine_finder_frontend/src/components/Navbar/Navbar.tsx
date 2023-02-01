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
    <>
      <div className="w-screen h-12 px-8 shadow-sm bg-gray-100 flex items-center border-b border-gray-200 relative">
        <Link className="text-lg font-semibold" to={"/"}>
          Medicine Finder
        </Link>
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
                        navigate("/auth");
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
      {!authenticatedUser?.user.user.verified && (
        <div className="absolute z-20 top-12 left-0 right-0 w-full h-16 bg-red-500 shadow-md text-white">
          <div className="flex justify-center items-center h-full gap-4">
            <span>
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
                  d="M21.75 6.75v10.5a2.25 2.25 0 01-2.25 2.25h-15a2.25 2.25 0 01-2.25-2.25V6.75m19.5 0A2.25 2.25 0 0019.5 4.5h-15a2.25 2.25 0 00-2.25 2.25m19.5 0v.243a2.25 2.25 0 01-1.07 1.916l-7.5 4.615a2.25 2.25 0 01-2.36 0L3.32 8.91a2.25 2.25 0 01-1.07-1.916V6.75"
                />
              </svg>
            </span>
            <span>
              Your email is not verified, Check your inbox for confirmation
              email.
            </span>
          </div>
        </div>
      )}
    </>
  );
};

export default Navbar;
