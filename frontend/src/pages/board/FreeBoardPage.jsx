import { Link, Outlet } from "react-router";

const FreeBoardPage = () => {
  return (
    <div>
      <Link to="/board/free/create">글 작성하기</Link>
      <Outlet />
    </div>
  );
};

export default FreeBoardPage;
