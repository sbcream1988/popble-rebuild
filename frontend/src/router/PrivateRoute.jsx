import { Navigate } from "react-router";
import { getAccessToken } from "../util/tokenStore";

const PrivateRoute = ({ children }) => {
  const token = getAccessToken;

  if (!token) {
    return <Navigate to="/user/login" replace></Navigate>;
  }
  return children;
};

export default PrivateRoute;
