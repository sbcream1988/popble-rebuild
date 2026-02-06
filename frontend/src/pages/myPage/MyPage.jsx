import { Outlet } from "react-router";
import BasicLayout from "../../layouts/BasicLayout";

const MyPage = () => {
  return (
    <BasicLayout>
      <h1>MyPage</h1>
      <Outlet></Outlet>
    </BasicLayout>
  );
};

export default MyPage;
