import { lazy, Suspense } from "react";
import PopupDetailComponent from "../components/popup/PopupDetailComponent";

const Create = lazy(() => import("../components/popup/popupCreateComponent"));
const List = lazy(() => import("../components/popup/PopupListComponent"));
const Detail = lazy(() => import("../components/popup/PopupDetailComponent"));
const DetailPage = lazy(() => import("../pages/popup/PopupDetailPage"));
const Image = lazy(() => import("../components/popup/PopupImageComponent"));
const Map = lazy(() => import("../components/popup/PopupMapComponent"));
const Reservation = lazy(
  () => import("../components/popup/PopupReservationComponent"),
);
const Review = lazy(() => import("../components/popup/PopupReviewComponent"));

const popupRouter = [
  // 팝업스토어 등록
  {
    path: "create",
    element: (
      <Suspense fallback={"..."}>
        <Create></Create>
      </Suspense>
    ),
  },

  // 팝업스토어 수정
  {
    path: ":id/edit",
    element: <Suspense fallback={"..."}></Suspense>,
  },
  // 팝업스토어 조회
  {
    path: ":id",
    element: (
      <Suspense fallback={"..."}>
        <DetailPage />
      </Suspense>
    ),
    children: [
      {
        index: true,
        element: (
          <Suspense fallback={"..."}>
            <Detail />
          </Suspense>
        ),
      },
      {
        path: "image",
        element: (
          <Suspense fallback={"..."}>
            <Image />
          </Suspense>
        ),
      },
      {
        path: "map",
        element: (
          <Suspense fallback={"..."}>
            <Map />
          </Suspense>
        ),
      },
      {
        path: "reservation",
        element: (
          <Suspense fallback={"..."}>
            <Reservation />
          </Suspense>
        ),
      },
      {
        path: "review",
        element: (
          <Suspense fallback={"..."}>
            <Review />
          </Suspense>
        ),
      },
    ],
  },
  // 팝업스토어 리스트 조회
  {
    path: "list",
    element: (
      <Suspense fallback={"..."}>
        <List></List>
      </Suspense>
    ),
  },
];

export default popupRouter;
