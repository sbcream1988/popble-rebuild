import { Suspense, lazy } from "react";
import PrivateRoute from "./privateRoute";

const Create = lazy(
  () => import("../components/board/FreeBoardCreateComponent"),
);
const List = lazy(() => import("../components/board/FreeBoardListComponent"));
const Detail = lazy(
  () => import("../components/board/FreeBoardDetailComponent"),
);
const Edit = lazy(() => import("../components/board/FreeBoardEditComponent"));

const freeBoardRouter = [
  //게시글 작성
  {
    path: "create",
    element: (
      <PrivateRoute>
        <Suspense fallback={"..."}>
          <Create></Create>
        </Suspense>
      </PrivateRoute>
    ),
  },
  //게시판 목록
  {
    path: "",
    element: (
      <Suspense fallback={"..."}>
        <List></List>
      </Suspense>
    ),
  },
  //글 상세
  {
    path: ":id",
    element: (
      <Suspense fallback={"..."}>
        <Detail></Detail>
      </Suspense>
    ),
  },
  //글 수정
  {
    path: ":id/edit",
    element: (
      <PrivateRoute>
        <Suspense fallback={"..."}>
          <Edit></Edit>
        </Suspense>
      </PrivateRoute>
    ),
  },
];

export default freeBoardRouter;
