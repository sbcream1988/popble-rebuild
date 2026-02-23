import { Children, lazy, Suspense } from "react";

const Info = lazy(() => import("../components/myPage/InfoComponent"));
const Update = lazy(() => import("../components/myPage/InfoUpdateComponent"));
const Popups = lazy(() => import("../components/myPage/MyPopupsComponent"));
const Slots = lazy(() => import("../components/myPage/MyPopupsSlotsComponent"));

const myPageRouter = [
  //내정보 (모든 유저)
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
  // 내 예약 정보
  {
    path: "reservation",
  },
  // === COMPANY 팝업 관리===
  {
    path: "popups",
    children: [
      // 등록한 팝업 목록
      {
        index: true,
        element: (
          <Suspense fallback={"Loading"}>
            <Popups></Popups>
          </Suspense>
        ),
      },
      // 슬롯 목록
      {
        path: ":popupId",
        element: (
          <Suspense fallback={"Loading"}>
            <Slots></Slots>
          </Suspense>
        ),
      },
    ],
  },
];

export default myPageRouter;
