import { Outlet } from "react-router";
import BasicLayout from "../../layouts/BasicLayout";

const PopupPage = () => {
  return (
    <BasicLayout>
      <Outlet></Outlet>
    </BasicLayout>
  );
};

export default PopupPage;
