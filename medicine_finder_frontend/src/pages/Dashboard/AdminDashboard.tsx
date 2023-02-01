import { Menu } from "@headlessui/react";
import React, { useContext, useEffect, useState } from "react";
import { useQuery, useMutation } from "react-query";
import { CellProps } from "react-table";
import {
  createProduct,
  deleteProduct,
  fetchPharmaProducts,
  updateProduct,
} from "../../api/product.api";
import Button from "../../components/Button/Button";
import CardWrapper from "../../components/Card/CardWrapper";
import MenuComponent from "../../components/Menu/MenuComponent";
import Modal from "../../components/Modal/Modal";
import Navbar from "../../components/Navbar/Navbar";
import AddProduct from "../../components/Product/AddProduct";
import Table from "../../components/Table/Table";
import { AuthContext } from "../../context/AuthContext";
import { IProduct, IProductAdd } from "../../utils/types";

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
        Cell: ({ row: { original } }: CellProps<any>) => {
          return <div>{original.available ? "Yes" : "No"}</div>;
        },
      },
      {
        Header: "Action",
        accessor: "action",
        Cell: ({ row: { id, original, index, values } }: CellProps<any>) => {
          const onEditClick = (value: IProduct) => {
            setEditData(value);
            setIsAddOpen({
              status: true,
              type: "EDIT",
            });
          };

          const onDeleteClick = (value: IProduct) => {
            handleDelete(value.id);
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
                      onClick={() => onEditClick(original)}
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
                      onClick={() => onDeleteClick(original)}
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

  const { data, refetch } = useQuery({
    queryKey: ["pharma-products", { id: authenticatedUser?.user.id }],
    queryFn: fetchPharmaProducts,
    enabled: !!authenticatedUser?.user.id,
  });

  const productAddMutation = useMutation({
    mutationFn: createProduct,
  });

  const productEditMutation = useMutation({
    mutationFn: updateProduct,
  });

  const productDeleteMutation = useMutation({
    mutationFn: deleteProduct,
  });

  const dataM = React.useMemo(() => {
    if (data) {
      return data.data.data.map((d, idx) => {
        return {
          ...d,
          id: d.id,
          index: idx + 1,
          // available: d.available ? "Yes" : "No",
        };
      });
    } else {
      return [];
    }
  }, [data]);

  const [isAddOpen, setIsAddOpen] = useState<{
    status: boolean;
    type?: "ADD" | "EDIT";
  }>({
    status: false,
  });
  const [editData, setEditData] = useState<IProduct>();

  const onAddEditModalSubmit = (value: IProductAdd & { id: string }) => {
    if (isAddOpen.type === "ADD") {
      productAddMutation.mutateAsync(value).then(() => {
        refetch();
      });
      setIsAddOpen({
        status: false,
      });
    } else {
      productEditMutation
        .mutateAsync({
          data: value,
          id: value.id,
        })
        .then(() => {
          refetch();
          setIsAddOpen({
            status: false,
          });
        });
    }
  };

  const handleDelete = (id: string) => {
    productDeleteMutation.mutateAsync(id).then(() => {
      refetch();
    });
  };

  return (
    <div>
      <Navbar />
      <div className="min-h-screen flex flex-col items-center justify-center mx-10">
        <div className="w-full flex justify-end">
          <Button
            onClick={() => {
              setEditData(undefined);
              setIsAddOpen({
                status: true,
                type: "ADD",
              });
            }}
          >
            Add
          </Button>
        </div>
        <Table columns={columns} data={dataM} />
      </div>
      <Modal
        title="Add Product"
        isOpen={isAddOpen.status}
        setIsOpen={setIsAddOpen}
      >
        <div>
          <AddProduct
            data={editData}
            onClose={() => {
              setIsAddOpen({
                status: false,
              });
            }}
            onSubmit={onAddEditModalSubmit}
          />
        </div>
      </Modal>
    </div>
  );
};

export default AdminDashboard;
