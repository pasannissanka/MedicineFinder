import { Form, Formik } from "formik";
import { Link, useNavigate } from "react-router-dom";
import * as Yup from "yup";
import { registerCustomerAPI } from "../../api/register.api";
import { ICreateCustomer } from "../../utils/types";
import FormField from "../FormField/FormField";

const SignupSchema = Yup.object().shape({
  email: Yup.string().email("Invalid email").required("Required"),
  password: Yup.string().min(6).required(),
  retypePassword: Yup.string().oneOf(
    [Yup.ref("password"), null],
    "Passwords must match"
  ),
  firstName: Yup.string().required(),
  lastName: Yup.string().required(),
});

export const RegisterCustomer = () => {
  const navigate = useNavigate();

  return (
    <div className="flex flex-col items-center">
      <div className="text-xl font-medium my-2">Register</div>
      <div className="border-b border-gray-200 w-full"></div>
      <Formik
        validationSchema={SignupSchema}
        initialValues={{
          email: "",
          password: "",
          retypePassword: "",
          firstName: "",
          lastName: "",
        }}
        onSubmit={async ({
          email,
          firstName,
          lastName,
          password,
          retypePassword,
        }) => {
          if (password === retypePassword) {
            const payload: ICreateCustomer = {
              email,
              firstName,
              lastName,
              password,
            };
            try {
              const response = await registerCustomerAPI(payload);
              navigate("/auth");
            } catch (error) {
              // TODO error handling
            }
          }
        }}
      >
        {() => (
          <Form className="my-4 px-4 flex flex-col gap-3 w-full ">
            <FormField name="email" placeholder="Email" type="email" />
            <FormField name="password" placeholder="Password" type="password" />
            <FormField
              name="retypePassword"
              placeholder="Retype Password"
              type="password"
            />
            <FormField type="text" name="firstName" placeholder="First Name" />
            <FormField type="text" name="lastName" placeholder="Last Name" />
            <div className="flex justify-between items-center">
              <span className="text-sm">
                Already have an account?
                <Link
                  className="ml-1 text-blue-700 hover:underline hover:text-blue-500 duration-150"
                  to={`/auth`}
                >
                  Login
                </Link>
              </span>
              <button
                type="submit"
                className="px-4 py-2 border bg-blue-700 text-white rounded-md hover:bg-blue-500 duration-150"
              >
                Sign up
              </button>
            </div>
          </Form>
        )}
      </Formik>
    </div>
  );
};
