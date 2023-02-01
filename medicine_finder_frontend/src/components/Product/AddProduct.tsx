import { Form, Formik } from "formik";
import React from "react";
import * as yup from "yup";
import { IProduct, IProductAdd } from "../../utils/types";
import Button from "../Button/Button";
import FormField from "../FormField/FormField";

const ProductInsertSchema = yup.object({
  brandName: yup.string().min(2),
  genericName: yup.string(),
  price: yup.number(),
  available: yup.boolean().default(true),
  description: yup.string(),
});

type AddProductProps = {
  type?: "EDIT" | "ADD";
  data?: IProduct;
  onClose: () => void;
  onSubmit: (value: IProductAdd & { id: string }) => void;
};

const AddProduct = ({
  onClose,
  onSubmit,
  data,
  type = "ADD",
}: AddProductProps) => {
  return (
    <div>
      <Formik<IProductAdd>
        initialValues={{
          available: data?.available === undefined ? true : data?.available,
          brandName: data?.brandName || "",
          description: data?.description || "",
          genericName: data?.genericName || "",
          price: data?.price || "",
        }}
        onSubmit={(value) => {
          onSubmit({ ...value, id: data?.id || "" });
        }}
        validationSchema={ProductInsertSchema}
      >
        {() => (
          <Form>
            <FormField
              type="text"
              name="brandName"
              placeholder="Brand Name"
              title="Brand Name"
            />
            <FormField
              type="text"
              name="genericName"
              placeholder="Generic Name"
              title="Generic Name"
            />
            <FormField
              type="text"
              name="description"
              placeholder="Description"
              title="Description"
            />
            <FormField
              type="number"
              name="price"
              placeholder="Price"
              title="Price"
            />
            <FormField
              type="checkbox"
              name="available"
              placeholder="Is Available"
              title="Availability"
            />
            <div className="flex justify-end gap-3">
              <Button className="" type="submit">
                {type === "ADD" ? "Add" : "Edit"}
              </Button>
              <Button
                onClick={onClose}
                className="bg-white text-black hover:bg-gray-300"
                type="button"
              >
                Close
              </Button>
            </div>
          </Form>
        )}
      </Formik>
    </div>
  );
};

export default AddProduct;
