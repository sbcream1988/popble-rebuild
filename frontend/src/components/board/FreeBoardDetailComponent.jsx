import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router";
import { deleteBoard, getBoard } from "../../api/freeBoardApi";

const FreeBoardDetailComponent = () => {
  const { id } = useParams();
  const [board, setBoard] = useState(null);

  const navigate = useNavigate();

  const handleDelete = async () => {
    const ok = window.confirm("삭제하시겠습니까?");
    if (!ok) {
      return;
    }

    try {
      await deleteBoard(id);
      alert("삭제완료");
      navigate("..");
    } catch (error) {
      console.error(error);
      alert("삭제실패");
    }
  };

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
        <div className="flex justify-between">
          <div className="p-2 font-bold text-sm">
            작성자: {board.writer?.email ?? "익명"}
          </div>
          <div className="flex justify-end">
            <div className="p-2 font-bold text-sm">조회수: </div>
            <div className="p-2 font-bold text-sm">추천수: </div>
            <div className="p-2 font-bold text-sm">
              작성일: {board.createdAt?.substring(0, 10)}
            </div>
          </div>
        </div>
      </div>
      {/* 내용 */}
      <div className="p-2 m-2 flex justify-start border-b border-sky-700">
        {board.content}
      </div>
      {/* 수정, 삭제 */}
      <div className="p-2 m-2 flex justify-between border-b border-sky-700">
        <Link to={".."}>
          <button className="p-2 m-2 rounded-2xl bg-sky-300 w-20 h-10 hover:bg-sky-400">
            목록
          </button>
        </Link>
        <div>
          <Link to={"edit"}>
            <button className="p-2 m-2 rounded-2xl bg-sky-300 w-20 h-10 hover:bg-sky-400">
              수정
            </button>
          </Link>
          <Link to={`/${id}`}>
            <button
              className="p-2 m-2 rounded-2xl bg-sky-300 w-20 h-10 hover:bg-sky-400"
              onClick={handleDelete}
            >
              삭제
            </button>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default FreeBoardDetailComponent;
