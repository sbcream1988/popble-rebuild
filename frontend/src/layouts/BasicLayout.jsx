import Footer from "./Footer";
import Header from "./Header";

function BasicLayout({ children }) {
  return (
    <div className="flex flex-col min-h-screen">
      {/* 상단 */}
      <Header className="fixed top-0 left-0 w-full z-50" />

      {/* 콘텐츠 영역 */}
      <main className="grow container mx-auto px-4 py-6">{children}</main>

      {/* 하단 */}
      <Footer />
    </div>
  );
}

export default BasicLayout;
