import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { getBoard } from "../../api/freeBoardApi";

const FreeBoardDetailComponent = () => {
  const { id } = useParams();
  const [board, setBoard] = useState(null);

  useEffect(() => {
    getBoard(id)
      .then((data) => {
        setBoard(data);
      })
      .catch((error) => console.error(error));
  }, [id]);

  if (!board) return <div>로딩중...</div>;

  return (
    <div className="max-w-4xl mx-auto p-6">
      {/* 제목 */}
      <h1 className="flex justify-start text-xl font-bold mb-4 p-4 bg-gray-200 border-t border-t-gray-400 border-b border-b-gray-400">
        {board.title}
      </h1>
      {/* 작성자, 일자 */}
      <div className="border-b border-sky-700">
        <div className="flex justify-start">
          <div className="p-2 font-bold text-sm">
            작성자: {board.writer?.email ?? "익명"}
          </div>
        </div>
        <div className="flex justify-end">
          <div className="p-2 font-bold text-sm">조회수: </div>
          <div className="p-2 font-bold text-sm">추천수: </div>
          <div className="p-2 font-bold text-sm">
            작성일: {board.createdAt?.substring(0, 10)}
          </div>
        </div>
      </div>
      {/* 내용 */}
      <div className="p-2 m-2 flex justify-start">{board.content}</div>
    </div>
  );
};

export default FreeBoardDetailComponent;
