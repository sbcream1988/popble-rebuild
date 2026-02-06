import { lazy, Suspense } from "react";

const Info = lazy(() => import("../components/myPage/InfoComponent"));

const myPageRouter = [
  //내정보
  {
    path: "info",
    element: (
      <Suspense fallback={"...Loading"}>
        <Info></Info>
      </Suspense>
    ),
  },
  {
    path: "update",
  },
  {
    path: "changePassword",
  },
];

export default myPageRouter;
