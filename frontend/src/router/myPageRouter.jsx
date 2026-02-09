import { lazy, Suspense } from "react";

const Info = lazy(() => import("../components/myPage/InfoComponent"));
const Update = lazy(() => import("../components/myPage/InfoUpdateComponent"));

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
    element: (
      <Suspense fallback={"로오오오오딩"}>
        <Update />
      </Suspense>
    ),
  },
  {
    path: "changePassword",
  },
];

export default myPageRouter;
