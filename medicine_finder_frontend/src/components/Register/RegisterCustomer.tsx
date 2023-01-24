import React from "react";
import { Formik, Field } from "formik";
import * as Yup from "yup";
import { Link } from "react-router-dom";

const SignupSchema = Yup.object().shape({
  email: Yup.string().email("Invalid email").required("Required"),
  password: Yup.string().min(6).required(),
  retype_password: Yup.string().oneOf(
    [Yup.ref("password"), null],
    "Passwords must match"
  ),
  firstName: Yup.string().required(),
  lastName: Yup.string().required(),
});

export const RegisterCustomer = () => {
  return (
    <div className="flex flex-col items-center">
      <div className="text-xl font-medium my-2">Register</div>
      <div className="border-b border-gray-200 w-full"></div>
      <Formik initialValues={{}} onSubmit={(value) => {}}>
        {() => (
          <div className="my-4 px-4 flex flex-col gap-2 w-full ">
            <Field type="email" name="email" placeholder="Email" />
            <Field type="password" name="password" placeholder="Password" />
            <Field
              type="password"
              name="retype_password"
              placeholder="Retype Password"
            />
            <Field type="text" name="firstName" placeholder="First Name" />
            <Field type="text" name="lastName" placeholder="Last Name" />
            <div className="flex justify-between items-center">
              <span className="text-sm">
                Already have an account?
                <Link className="ml-1 text-blue-700 hover:underline hover:text-blue-500 duration-150" to={`/auth/login`}>Login</Link>
              </span>
              <button className="px-4 py-2 border bg-blue-700 text-white rounded-md hover:bg-blue-500 duration-150">
                Sign up
              </button>
            </div>
          </div>
        )}
      </Formik>
    </div>
  );
};
