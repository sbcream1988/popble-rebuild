//페이지 UI

const PageComponent = ({ serverData, movePage }) => {
  if (!serverData) {
    return null;
  }

  return (
    <div className="flex justify-center items-center gap-2 mt-6">
      {/* 이전버튼 */}
      {serverData.hasPrev && (
        <button
          className="px-3 py-1 border-rounded"
          onClick={() => movePage({ page: serverData.prevPage })}
        >
          이전
        </button>
      )}

      {/* 페이지 */}
      {serverData.pageNumList.map((num) => (
        <button
          className={`px-3 py-1 border rounded ${serverData.currentPage === num ? "bg-blue-500 text-white" : ""}`}
          key={num}
          onClick={() => movePage({ page: num })}
        ></button>
      ))}

      {/* 다음버튼 */}
      {serverData.hasNext && (
        <button
          className="px-3 py-1 border-rounded"
          onClick={() => movePage({ page: serverData.nextPage })}
        >
          다음
        </button>
      )}
    </div>
  );
};

export default PageComponent;
