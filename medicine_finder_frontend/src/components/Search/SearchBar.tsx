import { Form, Formik, useFormik } from "formik";
import React, { useState } from "react";
import { useQuery } from "react-query";
import AsyncSelect from "react-select/async";
import { searchProducts } from "../../api/product.api";

const options = [
  { value: "chocolate", label: "Chocolate" },
  { value: "strawberry", label: "Strawberry" },
  { value: "vanilla", label: "Vanilla" },
];

const SearchBar = () => {
  const [searchParam, setSearchParam] = useState("");

  // const { refetch } = useQuery({
  //   queryFn: searchProducts,
  //   queryKey: ["productSearch", { name: searchParam }],
  //   enabled: searchParam.length > 0,
  // });

  const loadOptions = (
    inputValue: string,
    callback: (options: any[]) => void
  ) => {
    setSearchParam(inputValue);
    // refetch().then(({ data }) => {
    //   if (data?.data) {
    //     const options = data.data.data.map((p) => {
    //       return {
    //         value: p.id,
    //         label: p.brandName,
    //         data: p,
    //       };
    //     });
    //     callback(options);
    //   }
    // });
  };

  return (
    <div className="w-1/2">
      <AsyncSelect
        placeholder="Search Products"
        value={searchParam}
        onChange={(e, m) => console.log(e, m)}
        className="w-full"
        cacheOptions
        loadOptions={loadOptions}
        defaultOptions
      />
    </div>
  );
};

export default SearchBar;
