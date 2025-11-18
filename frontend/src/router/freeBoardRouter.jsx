import { Suspense, lazy } from "react";

const Create = lazy(() =>
  import("../components/board/FreeBoardCreateComponent")
);

const freeBoardRouter = [
  {
    path: "create",
    element: (
      <Suspense fallback={"..."}>
        <Create></Create>
      </Suspense>
    ),
  },
];

export default freeBoardRouter;
