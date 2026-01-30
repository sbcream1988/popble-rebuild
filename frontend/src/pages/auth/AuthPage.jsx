import { Outlet } from "react-router";
import BasicLayout from "../../layouts/BasicLayout";

const AuthPage = () => {
  return (
    <div>
      <BasicLayout>
        <h1>Auth Page</h1>
        <Outlet></Outlet>
      </BasicLayout>
    </div>
  );
};

export default AuthPage;
