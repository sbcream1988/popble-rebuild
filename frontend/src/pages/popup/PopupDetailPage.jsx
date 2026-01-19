import { NavLink, useParams } from "react-router";
import { Outlet } from "react-router";

const PopupDetailPage = () => {
  const { id } = useParams();

  const TabMenu = "p-2 text-2xl border border-sky-700 rounded-t-xl -mb-px";

  return (
    <div>
      <div className="flex">
        <NavLink
          to={`/popup/${id}`}
          className={({ isActive }) =>
            `${TabMenu} ${
              isActive
                ? "bg-white border-b-white"
                : "bg-sky-300 hover:bg-sky-400"
            }`
          }
        >
          정보
        </NavLink>
        <NavLink
          to={`/popup/${id}/image`}
          className={({ isActive }) =>
            `${TabMenu} ${
              isActive
                ? "bg-white border-b-white"
                : "bg-sky-300 hover:bg-sky-400"
            }`
          }
        >
          상세이미지
        </NavLink>
        <NavLink
          to={`/popup/${id}/map`}
          className={({ isActive }) =>
            `${TabMenu} ${
              isActive
                ? "bg-white border-b-white"
                : "bg-sky-300 hover:bg-sky-400"
            }`
          }
        >
          지도
        </NavLink>
        <NavLink
          to={`/popup/${id}/reservation`}
          className={({ isActive }) =>
            `${TabMenu} ${isActive ? "bg-white border-b-white" : "bg-sky-300 hover:bg-sky400"}`
          }
        >
          예약
        </NavLink>
        <NavLink
          to={`/popup/${id}/review`}
          className={({ isActive }) =>
            `${TabMenu} ${isActive ? "bg-white border-b-white" : "bg-sky-300 hover:bg-sky-400"}`
          }
        >
          리뷰
        </NavLink>
      </div>
      <div className="border border-sky-700 p-4">
        <Outlet></Outlet>
      </div>
    </div>
  );
};

export default PopupDetailPage;
