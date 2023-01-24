import React from "react";
import * as Yup from "yup";
import { Formik, Field } from "formik";
import { Link } from "react-router-dom";

const LoginSchema = Yup.object().shape({
  email: Yup.string().email("Invalid email").required("Required"),
  password: Yup.string().min(6).required(),
});

const Login = () => {
  return (
    <div className="flex flex-col items-center">
      <div className="text-xl font-medium my-2">Login</div>
      <div className="border-b border-gray-200 w-full"></div>
      <Formik initialValues={{}} onSubmit={(value) => {}}>
        {() => (
          <div className="my-4 px-4 flex flex-col gap-2 w-full ">
            <Field type="email" name="email" placeholder="Email" />
            <Field type="password" name="password" placeholder="Password" />
            <div className="flex justify-between items-center">
              <span className="text-sm">
                Customer: 
                <Link
                  className="ml-1 text-blue-700 hover:underline hover:text-blue-500 duration-150"
                  to={`/auth/register/customer`}
                >
                  Sign up
                </Link>
              </span>
              <button className="px-4 py-2 border bg-blue-700 text-white rounded-md hover:bg-blue-500 duration-150">
                Login
              </button>
            </div>
            <div className="text-sm flex justify-start">
              Pharmaceutical Institute: 
              <Link
                className="ml-1 text-blue-700 hover:underline hover:text-blue-500 duration-150"
                to={`/auth/register/pharmacy`}
              >
                Sign up
              </Link>
            </div>
          </div>
        )}
      </Formik>
    </div>
  );
};

export default Login;
