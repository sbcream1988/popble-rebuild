import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import { getMyPopups } from "../../api/popupApi";
import { API_SERVER_HOST } from "../../api/apiConfig";

const MyPopupsComponent = () => {
  const [popups, setPopups] = useState([]);

  const navigate = useNavigate();

  useEffect(() => {
    getMyPopups()
      .then(setPopups)
      .catch((e) => console.error("내 팝업목록 불러오기 실패", e));
  }, []);
  return (
    <div>
      <h1>내 팝업</h1>
      {popups.length === 0 ? (
        <p>등록한 팝업이 없습니다</p>
      ) : (
        <div className="w-full content-center">
          <table className="w-full table-fixed">
            <thead className="bg-blue-100">
              <tr>
                <th className="p-2 rounded-tl-2xl">대표 이미지</th>
                <th className="p-2 ">팝업 이름</th>
                <th className="p-2">팝업 장소</th>
                <th className="p-2">팝업 일정</th>
                <th className="p-2 rounded-tr-2xl">슬롯 관리</th>
              </tr>
            </thead>
            <tbody className="border-b border-sky-700 text-sm">
              {popups.map((popup) => (
                <tr key={popup.id}>
                  {/* 이미지 */}
                  <td className="p-2 border-b border-gray-200">
                    <img
                      src={`${API_SERVER_HOST}${popup.thumbnailUrl}`}
                      className="flex w-20 h-20 shrink-0 overflow-hidden rounded-xl"
                      alt={popup.title}
                    ></img>
                  </td>

                  {/* 팝업 이름 */}
                  <td className="p-2 border-b border-gray-200">
                    {popup.title}
                  </td>

                  {/* 팝업 장소 */}
                  <td className="p-2 border-b border-gray-200">
                    {popup.address}
                  </td>

                  {/* 팝업 일정 */}
                  <td className="p-2 border-b border-gray-200">
                    {popup.startDate} ~ {popup.endDate}
                  </td>

                  {/* 슬롯 관리 */}
                  <td className="p-2 border-b border-gray-200">
                    <button
                      className="p-2 rounded-xl bg-blue-200"
                      onClick={() => navigate(`${popup.id}`)}
                    >
                      추가/변경하기
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};

export default MyPopupsComponent;
