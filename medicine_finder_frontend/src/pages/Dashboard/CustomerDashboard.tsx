import React, { useContext } from "react";
import { useQuery } from "react-query";
import { getAllPharma } from "../../api/pharma.api";
import CardWrapper from "../../components/Card/CardWrapper";
import Navbar from "../../components/Navbar/Navbar";
import SearchBar from "../../components/Search/SearchBar";
import { AuthContext } from "../../context/AuthContext";

const CustomerDashboard = () => {
  const { authenticatedUser } = useContext(AuthContext);
  const { data } = useQuery({
    queryFn: getAllPharma,
    queryKey: ["pharma-all"],
  });

  return (
    <div className="relative">
      <Navbar />
      <div
        className={`absolute ${
          authenticatedUser?.user.user.verified ? "top-14" : "top-28"
        } my-2 w-full`}
      >
        <div className="w-full flex justify-center my-4">
          <SearchBar />
        </div>
        <div className="border-b my-8 mx-2"></div>
        <div className="flex flex-col items-center justify-center mx-10 my-2">
          <div className="w-full grid grid-cols-1 sm:grid-cols-5 gap-3">
            {data?.data.data.map((pharma) => (
              <CardWrapper key={pharma.id}>
                <span className="text-lg">{pharma.name}</span>
                <span className="text-sm text-gray-500">{pharma.details}</span>
              </CardWrapper>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};

export default CustomerDashboard;
