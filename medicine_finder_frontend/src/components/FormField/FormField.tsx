import React from "react";
import { Field, ErrorMessage } from "formik";

type FormFieldProps = {
  name: string;
  placeholder: string;
  type?: React.HTMLInputTypeAttribute;
};

const FormField = ({ name, placeholder, type }: FormFieldProps) => {
  return (
    <div className="flex flex-col w-full h-14">
      <Field type={type} name={name} placeholder={placeholder} />
      <ErrorMessage className="text-xs mt-1 text-red-700" component="div" name={name} />
    </div>
  );
};

export default FormField;
