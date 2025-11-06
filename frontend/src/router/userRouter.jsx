import { lazy, Suspense } from "react";

const Login = lazy(() => import("../pages/user/LoginPage"));
const Signup = lazy(() => import("../pages/user/SignupPage"));

const userRouter = [
  {
    path: "login",
    element: (
      <Suspense fallback={"...Loading"}>
        <Login></Login>
      </Suspense>
    ),
  },
  {
    path: "signup",
    element: (
      <Suspense fallback={"...Loading"}>
        <Signup></Signup>
      </Suspense>
    ),
  },
];

export default userRouter;
