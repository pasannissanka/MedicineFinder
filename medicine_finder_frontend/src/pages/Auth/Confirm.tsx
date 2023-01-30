import { useState } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import { verifyAccountApi } from "../../api/login.api";
import Button from "../../components/Button/Button";

const ConfirmPage = () => {
  let [searchParams] = useSearchParams();
  const [status, setStatus] = useState<{
    status: "idle" | "loading" | "done" | "error";
    message: string;
  }>({
    status: "idle",
    message: "Click confirm to verify your email address.",
  });

  const token = searchParams.get("token");
  const navigate = useNavigate();

  const onConfirm = () => {
    setStatus({
      ...status,
      status: "loading",
    });
    if (token) {
      verifyAccountApi({ token })
        .then((data) => {
          setStatus({
            ...status,
            status: "done",
            message: "Account confirmed, Please login in",
          });
        })
        .catch((err) => {
          setStatus({
            ...status,
            status: "error",
            message: "Failed to confirm account. Please try again.",
          });
        });
    }
  };

  return (
    <div className="flex flex-col gap-2 w-full items-center">
      <div className="text-xl font-medium my-2">Confirm Email</div>
      <div className="border-b border-gray-200 w-full"></div>
      <div className="my-4">
        {status.status === "loading" ? (
          <svg className="h-20 w-20 animate-spin" viewBox="3 3 18 18">
            <path
              className="fill-gray-200"
              d="M12 5C8.13401 5 5 8.13401 5 12C5 15.866 8.13401 19 12 19C15.866 19 19 15.866 19 12C19 8.13401 15.866 5 12 5ZM3 12C3 7.02944 7.02944 3 12 3C16.9706 3 21 7.02944 21 12C21 16.9706 16.9706 21 12 21C7.02944 21 3 16.9706 3 12Z"
            ></path>
            <path
              className="fill-gray-800"
              d="M16.9497 7.05015C14.2161 4.31648 9.78392 4.31648 7.05025 7.05015C6.65973 7.44067 6.02656 7.44067 5.63604 7.05015C5.24551 6.65962 5.24551 6.02646 5.63604 5.63593C9.15076 2.12121 14.8492 2.12121 18.364 5.63593C18.7545 6.02646 18.7545 6.65962 18.364 7.05015C17.9734 7.44067 17.3403 7.44067 16.9497 7.05015Z"
            ></path>
          </svg>
        ) : (
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            strokeWidth={1.5}
            stroke="currentColor"
            className="w-20 h-20"
          >
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              d="M21.75 9v.906a2.25 2.25 0 01-1.183 1.981l-6.478 3.488M2.25 9v.906a2.25 2.25 0 001.183 1.981l6.478 3.488m8.839 2.51l-4.66-2.51m0 0l-1.023-.55a2.25 2.25 0 00-2.134 0l-1.022.55m0 0l-4.661 2.51m16.5 1.615a2.25 2.25 0 01-2.25 2.25h-15a2.25 2.25 0 01-2.25-2.25V8.844a2.25 2.25 0 011.183-1.98l7.5-4.04a2.25 2.25 0 012.134 0l7.5 4.04a2.25 2.25 0 011.183 1.98V19.5z"
            />
          </svg>
        )}
      </div>
      <span>{status.message}</span>
      {status.status === "idle" && (
        <Button className="mt-2 mb-4" onClick={onConfirm}>
          Confirm
        </Button>
      )}
      {status.status === "done" && (
        <Button className="mt-2 mb-4" onClick={() => navigate("/auth")}>
          Login
        </Button>
      )}
    </div>
  );
};

export default ConfirmPage;
