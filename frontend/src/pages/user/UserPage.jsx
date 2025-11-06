import { Outlet } from "react-router";
import BasicLayout from "../../layouts/BasicLayout";

function UserPage() {
  return (
    <BasicLayout>
      <Outlet></Outlet>
    </BasicLayout>
  );
}

export default UserPage;
