import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router";
import { deleteBoard, editBoard, getBoard } from "../../api/freeBoardApi";

const FreeBoardEditComponent = () => {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [board, setBoard] = useState(null);
  const { id } = useParams();
  const navigate = useNavigate();

  //기본 글 불러오기
  useEffect(() => {
    getBoard(id).then((data) => {
      //표시(작성자, 조회수)
      setBoard(data);
      //수정
      setTitle(data.title);
      setContent(data.content);
    });
  }, [id]);

  //수정 submit
  const handleSubmit = async (error) => {
    error.preventDefault();

    try {
      await editBoard(id, { title, content });
      alert("게시글이 수정되었습니다");
      navigate(`/board/free/${id}`);
    } catch (error) {
      console.error(error);
      alert("게시글 수정에 실패했습니다");
    }
  };

  //삭제
  const handleDelete = async () => {
    if (!window.confirm("정말 삭제하시겠습니까?")) return;
    await deleteBoard(id);
    navigate("/board/free");
  };

  return (
    <div className="max-w-2xl mx-auto mt-10 p-6 bg-white shaodw rounded-lg">
      <h1 className="text-3xl p-2 m-2">수정화면</h1>
      <form className="" onSubmit={handleSubmit}>
        {/* 제목 */}
        <label className="flex font-bold mb-2">제목</label>
        <input
          type="text"
          className="w-full border border-sky-700 m-2 p-2"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        ></input>
        {/* 작성자, 작성일 */}
        <div className="m-2 p-2 flex justify-between font-bold bg-gray-100 text-sm">
          <div>
            <span className="justify-start p-2">
              작성자 : {board?.writer?.email ?? "익명"}
            </span>
          </div>
          <div>
            <span className="p-2">조회수 : </span>
            <span className="p-2">추천수 : </span>
            <span className="p-2">
              작성일 : {board?.createdAt?.substring(0, 10)}
            </span>
          </div>
        </div>
        {/* 내용 */}
        <label className="flex font-bold mb-2">내용</label>
        <textarea
          className="w-full border border-sky-700 m-2 p-2"
          rows={20}
          value={content}
          onChange={(e) => setContent(e.target.value)}
        ></textarea>
        {/* 버튼 */}
        <div className="flex justify-end">
          {/* 수정 */}
          <button className="m-2 p-2 rounded-2xl bg-sky-300" type="submit">
            수정
          </button>
          {/* 삭제 */}
          <button
            type="button"
            className="m-2 p-2 rounded-2xl bg-sky-300"
            onClick={handleDelete}
          >
            삭제
          </button>
        </div>
      </form>
    </div>
  );
};
export default FreeBoardEditComponent;
