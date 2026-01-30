import { createBrowserRouter } from "react-router";
import { lazy, Suspense } from "react";
import userRouter from "./userRouter";
import freeBoardRouter from "./freeBoardRouter";
import popupRouter from "./popupRouter";
import PopupPage from "../pages/popup/PopupPage";
import authRouter from "./authRouter";

const Home = lazy(() => import("../pages/MainPage"));
const User = lazy(() => import("../pages/user/UserPage"));
const FreeBoard = lazy(() => import("../pages/board/FreeBoardPage"));
const Board = lazy(() => import("../pages/board/BoardPage"));
const Auth = lazy(() => import("../pages/auth/AuthPage"));

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
    path: "auth",
    element: (
      <Suspense fallback={"..."}>
        <Auth></Auth>
      </Suspense>
    ),
    children: authRouter,
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
    path: "board",
    element: (
      <Suspense fallback={"..."}>
        <Board></Board>
      </Suspense>
    ),
    children: [
      //자유게시판
      {
        path: "free",
        element: (
          <Suspense fallback={"..."}>
            <FreeBoard />
          </Suspense>
        ),
        children: freeBoardRouter,
      },
      //공지
      {
        path: "notice",
      },
      //질문게시판
      {
        path: "qna",
      },
      //홍보
      {
        path: "promo",
      },
    ],
  },
  //팝업 라우터
  {
    path: "popup",
    element: (
      <Suspense fallback={"..."}>
        <PopupPage></PopupPage>
      </Suspense>
    ),
    children: popupRouter,
  },
]);

export default root;
