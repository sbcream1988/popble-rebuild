import { lazy, Suspense } from "react";

const Signup = lazy(() => import("../components/user/SignupComponent"));

const userRouter = [
  {
    path: "signup",
    element: (
      <Suspense fallback={"...."}>
        <Signup></Signup>
      </Suspense>
    ),
  },
];

export default userRouter;
