import { Menu } from "@headlessui/react";
import React, { useContext, useEffect } from "react";
import { useQuery } from "react-query";
import { CellProps } from "react-table";
import { fetchPharmaProducts } from "../../api/product.api";
import CardWrapper from "../../components/Card/CardWrapper";
import MenuComponent from "../../components/Menu/MenuComponent";
import Navbar from "../../components/Navbar/Navbar";
import Table from "../../components/Table/Table";
import { AuthContext } from "../../context/AuthContext";
import { IProduct } from "../../utils/types";

const AdminDashboard = () => {
  const columns = React.useMemo(
    () => [
      {
        Header: "#",
        accessor: "index", // accessor is the "key" in the data
      },
      {
        Header: "Brand Name",
        accessor: "brandName",
      },
      {
        Header: "Generic Name",
        accessor: "genericName",
      },
      {
        Header: "Price",
        accessor: "price",
      },
      {
        Header: "Description",
        accessor: "description",
      },
      {
        Header: "Availability",
        accessor: "available",
      },
      {
        Header: "Action",
        accessor: "action",
        Cell: ({ row: { id, original, index, values } }: CellProps<any>) => {
          const onItemClick = (value: any) => {
            console.log("value", value);
            // updateMyData(index, id, value)
          };
          return (
            <MenuComponent
              type="Rounded"
              size="sm"
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
                    d="M12 6.75a.75.75 0 110-1.5.75.75 0 010 1.5zM12 12.75a.75.75 0 110-1.5.75.75 0 010 1.5zM12 18.75a.75.75 0 110-1.5.75.75 0 010 1.5z"
                  />
                </svg>
              }
            >
              <div>
                <Menu.Item>
                  {({ active }) => (
                    <button
                      className={`${
                        active ? "bg-violet-500 text-white" : "text-gray-900"
                      } group flex w-full items-center rounded-md px-2 py-2 text-sm`}
                    >
                      Edit
                    </button>
                  )}
                </Menu.Item>
                <Menu.Item>
                  {({ active }) => (
                    <button
                      className={`${
                        active ? "bg-violet-500 text-white" : "text-gray-900"
                      } group flex w-full items-center rounded-md px-2 py-2 text-sm`}
                    >
                      Delete
                    </button>
                  )}
                </Menu.Item>
              </div>
            </MenuComponent>
          );
        },
      },
    ],
    []
  );

  const { authenticatedUser } = useContext(AuthContext);

  const { data } = useQuery({
    queryKey: ["pharma-products", { id: authenticatedUser?.user.id }],
    queryFn: fetchPharmaProducts,
    enabled: !!authenticatedUser?.user.id,
  });

  const dataM = React.useMemo(() => {
    if (data) {
      return data.data.data.map((d, idx) => {
        return {
          ...d,
          id: d.id,
          index: idx + 1,
          available: d.available ? "Yes" : "No",
        };
      });
    } else {
      return [];
    }
  }, [data]);

  console.log(data);

  return (
    <div>
      <Navbar />
      <div className="min-h-screen flex flex-col items-center justify-center mx-10">
        <Table columns={columns} data={dataM} />
      </div>
    </div>
  );
};

export default AdminDashboard;
