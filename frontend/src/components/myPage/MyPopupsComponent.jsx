import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import { getMyPopups } from "../../api/popupApi";

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
        <div>
          {popups.map((popup) => (
            <div key={popup.id} className="flex flex-row gap-4">
              <div>이미지</div>
              <div onClick={() => navigate(`/popup/${popup.id}/edit`)}>
                {popup.title}
              </div>
              <div>{popup.startDate}</div>
              <div>{popup.endDate}</div>
              <button onClick={() => navigate(`/MyPage/popups/${popup.id}`)}>
                슬롯 관리
              </button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default MyPopupsComponent;
