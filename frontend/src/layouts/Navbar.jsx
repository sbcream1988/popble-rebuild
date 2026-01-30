import { Link } from "react-router";
import { useState } from "react";

function Navbar() {
  const [open, setOpen] = useState(false);

  return (
    <div className="w-full h-20 bg-blue-200 flex justify-between items-center px-6">
      <Link to="/" className="" aria-label="Home">
        <div className="text-4xl">로고</div>
      </Link>
      {/* 데스크탑 화면 크기 클 때 */}
      <nav className="hidden md:flex gap-6 text-3xl items-center">
        <Link
          to="/"
          className="text-blue-950 font-bold px-2 py-1 hover:border-b-4 border-blue-900 transition-all"
        >
          Home
        </Link>
        <Link
          to="/popup"
          className="text-blue-950 font-bold px-2 py-1 hover:border-b-4 border-blue-900 transition-all"
        >
          팝업스토어
        </Link>
        <Link
          to="/board"
          className="text-blue-950 font-bold px-2 py-1 hover:border-b-4 border-blue-900 transition-all"
        >
          게시판
        </Link>
        <Link
          to="/mypage"
          className="text-blue-950 font-bold px-2 py-1  hover:border-b-4 border-blue-900 transition-all"
        >
          마이페이지
        </Link>
        <Link
          to="/auth/login"
          className="text-blue-950 font-bold px-2 py-1  hover:border-b-4 border-blue-900 transition-all"
        >
          로그인
        </Link>
      </nav>
      {/* 햄버거 버튼 모바일 또는 크기 작을때 */}
      <button onClick={() => setOpen(!open)} className="md:hidden p-2">
        햄버거
      </button>
      {/* 모바일 메뉴 */}
      {open && (
        <div className="absolute top-20 right-0 w-48 bg-sky-200 shadow-xl flex flex-col text-xl p-4 md:hidden">
          <Link
            to="/"
            className="p-2 hover:font-bold"
            onClick={() => setOpen(false)}
          >
            Home
          </Link>
          <Link
            to="/popup"
            className="p-2 hover:font-bold"
            onClick={() => setOpen(false)}
          >
            팝업스토어
          </Link>
          <Link
            to="/board"
            className="p-2 hover:font-bold"
            onClick={() => setOpen(false)}
          >
            게시판
          </Link>
          <Link
            to="/mypage"
            className="p-2 hover:font-bold"
            onClick={() => setOpen(false)}
          >
            마이페이지
          </Link>
          <Link
            to="/auth/login"
            className="p-2 hover:font-bold"
            onClick={() => setOpen(false)}
          >
            로그인
          </Link>
        </div>
      )}
    </div>
  );
}

export default Navbar;
