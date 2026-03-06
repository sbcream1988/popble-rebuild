import { Outlet } from "react-router";
import BasicLayout from "../../layouts/BasicLayout";

const AuthPage = () => {
  return (
    <div>
      <BasicLayout>
        <Outlet></Outlet>
      </BasicLayout>
    </div>
  );
};

export default AuthPage;
