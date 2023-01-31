import React, { Fragment } from "react";
import { Menu, Transition } from "@headlessui/react";

type MenuComponentProps = {
  type: "Button" | "Rounded";
  trigger: React.ReactNode;
  children: React.ReactNode;
  size?: "lg" | "md" | "sm";
};

const MenuComponent = ({
  type,
  trigger,
  children,
  size = "lg",
}: MenuComponentProps) => {
  const w = size === "lg" ? "w-56" : size === "md" ? "w-36" : "w-24";
  return (
    <Menu as="div" className="relative inline-block text-left">
      <Menu.Button
        className={`inline-flex w-full justify-center text-sm font-medium text-black focus:outline-none focus-visible:ring-2 focus-visible:ring-white focus-visible:ring-opacity-75
        ${
          type === "Button"
            ? "rounded-md border border-gray-300 px-4 py-1 bg-gray-100 hover:bg-gray-300 duration-200 "
            : "rounded-full p-2 bg-gray-100 hover:bg-gray-300 duration-200 "
        }
        `}
      >
        {trigger}
      </Menu.Button>
      <Transition
        as={Fragment}
        enter="transition ease-out duration-100"
        enterFrom="transform opacity-0 scale-95"
        enterTo="transform opacity-100 scale-100"
        leave="transition ease-in duration-75"
        leaveFrom="transform opacity-100 scale-100"
        leaveTo="transform opacity-0 scale-95"
      >
        <Menu.Items
          className={`z-10 absolute right-0 ${w} origin-top-right divide-y divide-gray-100 rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none`}
        >
          <div className="px-1 py-1">{children}</div>
        </Menu.Items>
      </Transition>
    </Menu>
  );
};

export default MenuComponent;
