import { QueryClient, QueryClientProvider } from "react-query";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { meAPI } from "./api/login.api";
import Login from "./components/Login/Login";
import ProtectedLayout from "./components/Protected/ProtectedLayout";
import Register from "./components/Register";
import { AuthProvider } from "./context/AuthContext";
import AuthPage from "./pages/Auth/Auth";
import ConfirmPage from "./pages/Auth/Confirm";
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
        const data = await meAPI();
        return data;
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
        path: "confirm",
        element: <ConfirmPage />,
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

const queryClient = new QueryClient();

function App() {
  return (
    <div className="flex justify-center">
      <AuthProvider>
        <QueryClientProvider client={queryClient}>
          <RouterProvider router={router} />
        </QueryClientProvider>
      </AuthProvider>
    </div>
  );
}

export default App;
