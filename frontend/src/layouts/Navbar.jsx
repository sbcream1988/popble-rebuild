import { Link } from "react-router";
import { useState, useRef, useEffect } from "react";
import { useLocation } from "react-router";
import { useSelector, useDispatch } from "react-redux";
import { logout as logoutAction } from "../store/authSlice";
import { logout as apiLogout } from "../api/AuthApi";

function Navbar() {
  // 햄버거 메뉴 선택
  const [open, setOpen] = useState(false);
  // 하위 메뉴 오픈
  const [openMenu, setOpenMenu] = useState(null);

  const dispatch = useDispatch();
  const isLoggedIn = useSelector((state) => state.auth.isLoggedIn);
  const user = useSelector((state) => state.auth.user);

  const menuRef = useRef(null);
  const location = useLocation();

  const handleLogout = async () => {
    try {
      await apiLogout();
    } catch (error) {
      console.error(error);
    } finally {
      dispatch(logoutAction());
    }
  };

  //외부 클릭시 닫기
  useEffect(() => {
    const handleClickOutside = (element) => {
      if (menuRef.current && !menuRef.current.contains(element.target)) {
        setOpenMenu(null);
      }
    };

    document.addEventListener("mousedown", handleClickOutside);
    return () => document.removeEventListener("mousedown", handleClickOutside);
  }, []);

  //페이지 이동 시 닫기
  useEffect(() => {
    setOpenMenu(null);
    setOpen(false);
  }, [location]);

  return (
    <div className="w-full h-20 bg-blue-200 flex justify-between items-center px-6">
      <Link to="/" className="" aria-label="Home">
        <div className="text-4xl">로고</div>
      </Link>
      {/* 데스크탑 화면 크기 클 때 */}
      <nav ref={menuRef} className="hidden md:flex gap-6 text-3xl items-center">
        {/* 홈 */}
        <Link
          to="/"
          className="relative font-bold text-blue-700 px-2 py-1 after:absolute after:left-0 after:-bottom-1 after:h-[3px] after:w-0 after:bg-blue-900 after:transition-all after:duration-300 hover:after:w-full"
        >
          Home
        </Link>
        {/* 팝업 */}
        <div className="relative">
          <button
            onClick={() => setOpenMenu(openMenu === "popup" ? null : "popup")}
            className="relative font-bold text-blue-700 px-2 py-1 after:absolute after:left-0 after:-bottom-1 after:h-[3px] after:w-0 after:bg-blue-900 after:transition-all after:duration-300 hover:after:w-full"
          >
            팝업스토어
          </button>
          {openMenu === "popup" && (
            <div className="absolute top-15 left-0 bg-white shadow-md rounded-md flex flex-col text-lg w-40">
              <Link to="/popup/list" className="p-2 hover:bg-blue-100">
                팝업 목록
              </Link>
              {user?.role === "ROLE_ADMIN" || user?.role === "ROLE_COMPANY" ? (
                <Link to="/popup/create" className="p-2 hover:bg-blue-100">
                  팝업 등록
                </Link>
              ) : null}
            </div>
          )}
        </div>
        {/* 게시판 */}
        <div className="relative">
          <button
            onClick={() => setOpenMenu(openMenu === "board" ? null : "board")}
            className="relative font-bold text-blue-700 px-2 py-1 after:absolute after:left-0 after:-bottom-1 after:h-[3px] after:w-0 after:bg-blue-900 after:transition-all after:duration-300 hover:after:w-full"
          >
            게시판
          </button>
          {openMenu === "board" && (
            <div className="absolute top-15 left-0 bg-white shadow-md rounded-md flex flex-col text-lg w-40">
              <Link to="/board/notice" className="p-2 hover:bg-blue-100">
                공지게시판
              </Link>
              <Link to="/board/free" className="p-2 hover:bg-blue-100">
                자유게시판
              </Link>
              <Link to="/board/qna" className="p-2 hover:bg-blue-100">
                QNA게시판
              </Link>
              <Link to="/board/promo" className="p-2 hover:bg-blue-100">
                홍보게시판
              </Link>
            </div>
          )}
        </div>
        {/* 마이페이지 */}
        <div className="relative">
          <button
            onClick={() => setOpenMenu(openMenu === "myPage" ? null : "myPage")}
            className="relative font-bold text-blue-700 px-2 py-1 after:absolute after:left-0 after:-bottom-1 after:h-[3px] after:w-0 after:bg-blue-900 after:transition-all after:duration-300 hover:after:w-full"
          >
            마이페이지
          </button>
          {openMenu === "myPage" && (
            <div className="absolute top-15 left-0 bg-white shadow-md rounded-md flex flex-col text-lg w-40">
              <Link to="/myPage/info" className="p-2 hover:bg-blue-100">
                내 정보
              </Link>
              <Link to="/myPage/reservation">내 예약</Link>
              <Link to="/myPage/popups">내 팝업</Link>
            </div>
          )}
        </div>
        {/* 로그인 */}
        {isLoggedIn ? (
          <>
            <span className="text-sm">{user?.nickname}님 환영합니다</span>
            <button onClick={handleLogout} className="bg-white">
              로그아웃
            </button>
          </>
        ) : (
          <Link
            to="/auth/login"
            className="text-blue-950 font-bold px-2 py-1  hover:border-b-4 border-blue-900 transition-all"
          >
            로그인
          </Link>
        )}
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
            to="/my"
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
