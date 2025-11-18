import { createBrowserRouter } from "react-router";
import { lazy, Suspense } from "react";
import userRouter from "./userRouter";
import freeBoardRouter from "./freeBoardRouter";

const Home = lazy(() => import("../pages/MainPage"));
const User = lazy(() => import("../pages/user/UserPage"));
const FreeBoard = lazy(() => import("../pages/board/FreeBoardPage"));

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
  {
    path: "board/free",
    element: (
      <Suspense fallback={"..."}>
        <FreeBoard></FreeBoard>
      </Suspense>
    ),
    children: freeBoardRouter,
  },
]);

export default root;
