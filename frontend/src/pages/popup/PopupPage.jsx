import { Outlet } from "react-router";
import BasicLayout from "../../layouts/BasicLayout";

const PopupPage = () => {
  return (
    <BasicLayout>
      <div>
        <h1 className="text-4xl font-bold">팝업페이지</h1>
        <Outlet></Outlet>
      </div>
    </BasicLayout>
  );
};

export default PopupPage;
