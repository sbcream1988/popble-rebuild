import { Suspense } from "react";
import LoginPage from "../pages/user/LoginPage";

const authRouter = [
  {
    path: "login",
    element: (
      <Suspense fallback={"...Loading"}>
        <LoginPage></LoginPage>
      </Suspense>
    ),
  },
];

export default authRouter;
