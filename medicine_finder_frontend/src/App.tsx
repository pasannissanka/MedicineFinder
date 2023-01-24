import { useState } from "react";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Login from "./components/Login/Login";
import Register from "./components/Register";
import AuthPage from "./pages/Auth/Auth";
import NotFoundPage from "./pages/Error/NotFoundPage";
import { USER_TYPES } from "./utils/enum";

const router = createBrowserRouter([
  {
    path: "/",
    element: <div>Hello world!</div>,
    errorElement: <NotFoundPage />,
  },
  {
    path: "/auth",
    element: <AuthPage />,
    children: [
      {
        path: "login",
        element: <Login />,
      },
      {
        path: "register/customer",
        element: <Register type={USER_TYPES.CUSTOMER} />,
      },
      {
        path: "register/pharmacy",
        element: <Register type={USER_TYPES.PHARMA} />,
      },
    ],
  },
]);

function App() {
  const [count, setCount] = useState(0);

  return (
    <div className="flex justify-center">
      <RouterProvider router={router} />
    </div>
  );
}

export default App;
