function Header() {
  return (
    <header className="bg-blue-300 text-white p-4">
      <div className="flex justify-between items-center max-w-7xl mx-auto">
        <div className="w-1/6">홈</div>
        <nav className="space-x-10">
          <span>팝업스토어</span>
          <span>게시판</span>
          <span>로그인</span>
        </nav>
      </div>
    </header>
  );
}

export default Header;
