import { Formik, Form } from "formik";
import { useContext } from "react";
import { Link } from "react-router-dom";
import * as Yup from "yup";
import { loginAPI } from "../../api/login.api";
import { AuthContext } from "../../context/AuthContext";
import FormField from "../FormField/FormField";

export interface LoginReqBody {
  email: string;
  password: string;
}

const LoginSchema = Yup.object().shape({
  email: Yup.string().email("Invalid email").required("Required"),
  password: Yup.string().min(6).required(),
});

const Login = () => {
  const { login } = useContext(AuthContext);
  return (
    <div className="flex flex-col items-center">
      <div className="text-xl font-medium my-2">Login</div>
      <div className="border-b border-gray-200 w-full"></div>
      <Formik<LoginReqBody>
        validationSchema={LoginSchema}
        initialValues={{
          email: "",
          password: "",
        }}
        onSubmit={async (value) => {
          if (value) {
            const data = await loginAPI(value);
            if (data && data.data.token) {
              login(data?.data.token);
            }
          }
        }}
      >
        {() => (
          <Form className="my-4 px-4 flex flex-col gap-2 w-full ">
            <FormField type="email" name="email" placeholder="Email" />
            <FormField type="password" name="password" placeholder="Password" />
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
              <button
                type="submit"
                className="px-4 py-2 border bg-blue-700 text-white rounded-md hover:bg-blue-500 duration-150"
              >
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
          </Form>
        )}
      </Formik>
    </div>
  );
};

export default Login;
