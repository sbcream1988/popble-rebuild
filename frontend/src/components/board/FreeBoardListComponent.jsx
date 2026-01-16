import { useEffect, useState } from "react";
import { getList } from "../../api/freeBoardApi";
import { Link, useNavigate } from "react-router";

const FreeBoardListComponent = () => {
  const [list, setList] = useState([]);

  const navigate = useNavigate();

  useEffect(() => {
    getList()
      .then((data) => {
        setList(data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);
  return (
    <div>
      <div className="w-full content-center">
        <table className="w-full table-fixed">
          <thead className="bg-sky-300">
            <tr>
              <th className="w-1/12 p-2 rounded-tl-2xl">번호</th>
              <th className="w-5/12 p-2">제목</th>
              <th className="w-3/12 p-2">작성자</th>
              <th className="w-2/12 p-2">작성일</th>
              <th className="w-1/12 p-2">조회수</th>
              <th className="w-1/12 p-2 rounded-tr-2xl">추천수</th>
            </tr>
          </thead>
          <tbody className="border-b border-sky-700 text-sm">
            {list.map((board) => (
              <tr
                key={board.id}
                onClick={() => navigate(`${board.id}`)}
                className="hover:bg-gray-100 cursor-pointer transition"
              >
                <td className="p-2 border-b border-gray-200">{board.id}</td>
                <td className="p-2 border-b border-gray-200">{board.title}</td>
                <td className="p-2 border-b border-gray-200">
                  {board.writer?.email ?? "익명"}
                </td>
                <td className="p-2 border-b border-gray-200">
                  {board?.createdAt?.substring(0, 10)}
                </td>
                <td className="p-2 border-b border-gray-200"></td>
                <td className="p-2 border-b border-gray-200"></td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      {/* 버튼 부분 */}
      <div className="flex justify-end mt-4">
        <Link to={"create"}>
          <button className="m-2 p-2 bg-sky-300 rounded-xl text-sm hover:bg-sky-400">
            작성하기
          </button>
        </Link>
      </div>
    </div>
  );
};

export default FreeBoardListComponent;
