import { Form, Formik } from "formik";
import { Link, useNavigate } from "react-router-dom";
import * as Yup from "yup";
import { registerPharmaAPI } from "../../api/register.api";
import FormField from "../FormField/FormField";
import Map from "../Map/Map";

const SignupSchema = Yup.object().shape({
  email: Yup.string().email("Invalid email").required("Required"),
  password: Yup.string().min(6).required(),
  retype_password: Yup.string().oneOf(
    [Yup.ref("password"), null],
    "Passwords must match"
  ),
  name: Yup.string().optional(),
  address: Yup.string().optional(),
  details: Yup.string().optional(),
});

export const RegisterPharma = () => {
  const navigate = useNavigate();
  return (
    <div className="flex flex-col items-center">
      <div className="text-xl font-medium my-2">
        Register as Pharmaceutical Institute
      </div>
      <div className="border-b border-gray-200 w-full"></div>
      <Formik
        validationSchema={SignupSchema}
        initialValues={{
          address: "",
          details: "",
          email: "",
          password: "",
          retype_password: "",
          name: ""
        }}
        onSubmit={async (values) => {
          if (values.password === values.retype_password) {
            try {
              const response = await registerPharmaAPI({
                address: values.address,
                details: values.details,
                email: values.email,
                password: values.password,
                name: values.name
              });
              navigate("/auth")
            } catch (error) {
              // TODO error handling
            }
          }
        }}
      >
        {({ }) => (
          <>
            <Form className="my-4 px-4 w-full">
              <div className="flex flex-col gap-2 border-b pb-2">
                <FormField type="email" name="email" placeholder="Email" />
                <FormField
                  type="password"
                  name="password"
                  placeholder="Password"
                />
                <FormField
                  type="password"
                  name="retype_password"
                  placeholder="Retype Password"
                />
                <FormField type="text" name="address" placeholder="Address" />
                <FormField
                  type="text"
                  name="details"
                  placeholder="Additional Details"
                />
                <div className="flex  justify-between items-center">
                  <span className="text-sm">
                    Already have an account?
                    <Link
                      className="ml-1 text-blue-700 hover:underline hover:text-blue-500 duration-150"
                      to={`/auth`}
                    >
                      Login
                    </Link>
                  </span>
                  <button className="px-4 py-2 border bg-blue-700 text-white rounded-md hover:bg-blue-500 duration-150">
                    Sign up
                  </button>
                </div>
              </div>
              <div className="flex justify-start my-2">
                <span>Select location</span>
              </div>
              <Map />
            </Form>
          </>
        )}
      </Formik>
    </div>
  );
};
