import { createBrowserRouter } from "react-router";
import { lazy, Suspense } from "react";
import userRouter from "./userRouter";

const Home = lazy(() => import("../pages/MainPage"));
const User = lazy(() => import("../pages/user/UserPage"));

const root = createBrowserRouter([
  {
    path: "/",
    element: (
      <Suspense fallback={"..."}>
        <Home></Home>
      </Suspense>
    ),
  },
  {
    path: "user",
    element: (
      <Suspense fallback={"..."}>
        <User></User>
      </Suspense>
    ),
    children: userRouter,
  },
]);

export default root;
