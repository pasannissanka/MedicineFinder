import { Suspense } from "react";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { meAPI } from "./api/login.api";
import Login from "./components/Login/Login";
import ProtectedLayout from "./components/Protected/ProtectedLayout";
import Register from "./components/Register";
import { AuthProvider } from "./context/AuthContext";
import AuthPage from "./pages/Auth/Auth";
import Dashboard from "./pages/Dashboard/Dashboard";
import NotFoundPage from "./pages/Error/NotFoundPage";
import { USER_TYPES } from "./utils/enum";

const router = createBrowserRouter([
  {
    path: "/",
    element: <ProtectedLayout />,
    errorElement: <NotFoundPage />,
    loader: async () => {
      try {
        const data =  await meAPI();
        return data
      } catch (error) {
        return null;
      }
    },
    children: [
      {
        path: "/",
        element: <Dashboard />,
      },
    ],
  },
  {
    path: "/auth",
    element: <AuthPage />,
    children: [
      {
        path: "",
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
  return (
    <div className="flex justify-center">
      <AuthProvider>
        <RouterProvider router={router} />
      </AuthProvider>
    </div>
  );
}

export default App;
