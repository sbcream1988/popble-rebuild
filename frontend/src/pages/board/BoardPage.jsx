import { Link, Outlet, NavLink } from "react-router";
import BasicLayout from "../../layouts/BasicLayout";

const boardTab = "p-2 text-2xl border border-sky-700 rounded-t-xl -mb-px";

const BoardPage = () => {
  return (
    <BasicLayout>
      <div className="max-w-5xl mx-auto p-6">
        <h1 className="text-5xl font-bold mb-4">게시판</h1>
        <div className="flex">
          <NavLink
            to="/board/notice"
            className={({ isActive }) =>
              `${boardTab} ${
                isActive
                  ? "bg-white border-b-white"
                  : "bg-sky-300 hover:bg-sky-400"
              }`
            }
          >
            공지
          </NavLink>
          <NavLink
            to={"/board/free"}
            className={({ isActive }) =>
              `${boardTab} ${
                isActive
                  ? "bg-white border-b-white"
                  : "bg-sky-300 hover:bg-sky-400"
              }`
            }
          >
            자유
          </NavLink>
          <NavLink
            to={"/board/qna"}
            className={({ isActive }) =>
              `${boardTab} ${
                isActive
                  ? "bg-white border-b-white"
                  : "bg-sky-300 hover:bg-sky-400"
              }`
            }
          >
            질문
          </NavLink>
          <NavLink
            to={"/board/promo"}
            className={({ isActive }) =>
              `${boardTab} ${
                isActive
                  ? "bg-white border-b-white"
                  : "bg-sky-300 hover:bg-sky-400"
              }`
            }
          >
            홍보
          </NavLink>
        </div>
        <div className="border border-sky-700 p-4">
          <Outlet />
        </div>
      </div>
    </BasicLayout>
  );
};

export default BoardPage;
