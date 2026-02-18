import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import { getPopupList } from "../../api/popupApi";
import { API_SERVER_HOST } from "../../api/apiConfig";

const PopupListComponent = () => {
  const [list, setList] = useState([]);

  //검색어 상태
  const [searchTerms, setSearchTerms] = useState("");

  const navigate = useNavigate();

  const filteredList = list.filter(
    (popup) =>
      popup.title.toLowerCase().includes(searchTerms.toLowerCase()) ||
      popup.address.toLowerCase().includes(searchTerms.toLowerCase()),
  );

  useEffect(() => {
    getPopupList()
      .then((data) => {
        setList(data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);
  return (
    <div className="max-w-2xl mx-auto mt-10 p-6 bg-white shadow-lg">
      {/* 검색어 UI */}
      <div className="relative mb-6">
        <input
          type="text"
          placeholder="팝업스토어 이름이나 지역을 검색해보세요"
          className="w-full p-4 pl-12 border-2 border-purple-100 rounded-3xl focus:border-purple-400 outline-none transition-all"
          value={searchTerms}
          onChange={(e) => {
            setSearchTerms(e.target.value);
          }}
        ></input>
        <span className="absolute left-4 top-4 text-xl"></span>
      </div>
      <hr className="p-2"></hr>

      {/* 카드 리스트 시작 */}
      <div className="grid grid-cols-1 gap-4 md:grid-cols-2 md:gap-6">
        {filteredList.map((popup) => (
          <div
            key={popup.id}
            className="flex w-full h-36 border border-gray-500 rounded-2xl hover:cursor-pointer transition p-2 bg-purple-200 shadow-md shadow-gray-400"
            onClick={() => navigate(`../${popup.id}`)}
          >
            {/* 이미지 고정 */}
            <div className="w-32 h-full shrink-0 overflow-hidden rounded-xl">
              <img
                src={`${API_SERVER_HOST}${popup.thumbnailUrl}`}
                className="w-full h-full object-cover rounded-2xl border border-purple-400"
                alt={popup.title}
              ></img>
            </div>
            {/* 텍스트정보 */}
            <div className="flex flex-col justify-between py-1 ml-4 overflow-hidden w-full text-left">
              <div className="text-lg font-bold truncate text-gray-800 mb-1 leading-tight">
                {popup.title}
              </div>
              <div className="text-sm text-gray-500 truncate leading-tight mb-1">
                <span className="inline-block mr-1"></span>
                {popup.address}
              </div>
              <div className="text-xs text-gray-400 flex items-center">
                <span className="w-14 bg-purple-300 text-gray-700 px-2 py-0.5 rounded-full mr-2 font-medium text-center">
                  일정
                </span>
                {popup.startDate} ~ {popup.endDate}
              </div>
            </div>
          </div>
        ))}
      </div>

      {/* 검색결과 없을때 없다고 처리 */}
      {filteredList.length === 0 && (
        <div className="text-center py-20 text-gray-400">
          찾으시는 내용이 없어요
        </div>
      )}
    </div>
  );
};
export default PopupListComponent;
