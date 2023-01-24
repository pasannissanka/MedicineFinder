import React from "react";
import { Formik } from "formik";
import { USER_TYPES } from "../../utils/enum";
import { RegisterCustomer } from "./RegisterCustomer";
import { RegisterPharma } from "./RegisterPharma";

const Register = ({ type }: { type: USER_TYPES }) => {
  return (
    <>
      {type === USER_TYPES.CUSTOMER ? <RegisterCustomer /> : <RegisterPharma />}
    </>
  );
};

export default Register;
