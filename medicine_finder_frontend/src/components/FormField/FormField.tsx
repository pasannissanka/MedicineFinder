import React from "react";
import { Field, ErrorMessage } from "formik";

type FormFieldProps = {
  name: string;
  placeholder: string;
  title: string;
  type?: React.HTMLInputTypeAttribute;
};

const FormField = ({ name, placeholder, type, title }: FormFieldProps) => {
  return (
    <div
      className={`flex ${
        type !== "checkbox"
          ? "flex-col my-2"
          : "flex-row items-center gap-4 my-5"
      } w-full`}
    >
      <label className="text-sm text-gray-500" htmlFor={name}>
        {title}
      </label>
      <div className="flex flex-col">
        <Field
          className="rounded-sm"
          type={type}
          id={name}
          name={name}
          placeholder={placeholder}
        />
        <ErrorMessage
          className="text-xs mt-1 text-red-700"
          component="div"
          name={name}
        />
      </div>
    </div>
  );
};

export default FormField;
