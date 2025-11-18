import { useState } from "react";
import { useNavigate } from "react-router";
import { create } from "../../api/freeBoardApi";

const FreeBoardCreateComponent = () => {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const boardDTO = {
      title,
      content,
    };

    try {
      const response = await create(boardDTO);
      alert("게시글 등록이 완료되었습니다");
      navigate("/board/free/list");
    } catch (e) {
      alert("게시글 등록 중 오류가 발생했습니다.");
    }
  };

  return (
    <div className="max-w-2xl mx-auto mt-10 p-6 bg-white shadow rounded-lg">
      <h1>자유 게시판 게시글 작성</h1>
      <form onSubmit={handleSubmit} className="space-y-4">
        {/* 제목 */}
        <div>
          <label className="block font-medium mb-1">제목</label>
          <input
            type="text"
            className="w-full border p-2 rounded"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            placeholder="제목을 입력하세요"
            required
          ></input>
        </div>
        {/* 내용*/}
        <div>
          <label className="block font-medium mb-1">내용</label>
          <textarea
            className="w-full border p-2 rounded"
            value={content}
            onChange={(e) => setContent(e.target.value)}
            placeholder="내용을 입력하세요"
            required
          ></textarea>
        </div>

        <button
          type="submit"
          className="bg-blue-400 text-white px-4 py-2 rounded-4xl hover:bg-blue-500"
        >
          등록하기
        </button>
      </form>
    </div>
  );
};

export default FreeBoardCreateComponent;
