import { Suspense, lazy } from "react";

const Create = lazy(() =>
  import("../components/board/FreeBoardCreateComponent")
);
const List = lazy(() => import("../components/board/FreeBoardListComponent"));
const Detail = lazy(() =>
  import("../components/board/FreeBoardDetailComponent")
);
const Edit = lazy(() => import("../components/board/FreeBoardEditComponent"));

const freeBoardRouter = [
  //게시글 작성
  {
    path: "create",
    element: (
      <Suspense fallback={"..."}>
        <Create></Create>
      </Suspense>
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
      <Suspense fallback={"..."}>
        <Edit></Edit>
      </Suspense>
    ),
  },
];

export default freeBoardRouter;
