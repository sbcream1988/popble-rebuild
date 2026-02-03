import { useNavigate, useSearchParams } from "react-router";

const useCustomMove = () => {
  const navigate = useNavigate();
  const [query] = useSearchParams();

  const page = parseInt(query.get("page")) || 1;
  const size = parseInt(query.get("size")) || 10;

  const movePage = ({ page, size = 10 }) => {
    navigate(`?page=${page}&size=${size}`);
  };
  return { page, size, movePage };
};

export default useCustomMove;
