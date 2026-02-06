import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router";
import { withdrawMember, getMyInfo } from "../../api/myPageApi";

const Info = () => {
  const [myInfo, setMyInfo] = useState(null);

  const navigate = useNavigate();

  const handleDelete = async () => {
    const ok = window.confirm("탈퇴 하시겠습니까?");
    if (!ok) {
      return;
    }
    try {
      await withdrawMember();
      alert("회원 탈퇴 완료");
      navigate("/");
    } catch (e) {
      console.error("탈퇴에 실패했습니다", e);
      alert("회원 탈퇴 실패");
    }
  };

  useEffect(() => {
    getMyInfo()
      .then((data) => {
        console.log("사용자 데이터", data);
        setMyInfo(data);
      })
      .catch((e) => console.error("사용자 데이터 불러오기 실패", e));
  }, []);

  if (!myInfo) {
    return <div>로오오오딩중</div>;
  }

  return (
    <div>
      <h1 className="p-2 m-2">내 정보</h1>

      <div className="w-11/12 mx-auto">
        {/* 정보 */}
        {/* 아이디 */}
        <div className="flex flex-row border-b border-b-gray-400 border-t-2 border-t-gray-400">
          <label className="p-4 flex flex-wrap w-1/4 text-xl bg-gray-200">
            아이디(이메일)
          </label>
          <span className="p-4 flex flex-wrap w-3/4 justify-center bg-gray-100 text-xl font-bold">
            {myInfo?.email}
          </span>
        </div>
        {/* 닉네임 */}
        <div className="flex flex-row border-b border-gray-400">
          <label className="p-4 flex flex-wrap w-1/4 text-xl bg-gray-200">
            닉네임
          </label>
          <span className="p-4 flex flex-wrap w-3/4 justify-center bg-gray-100 text-xl font-bold">
            {myInfo?.nickname}
          </span>
        </div>
        {/* 연락처 */}
        <div className="flex flex-row border-b border-gray-400">
          <label className="p-4 flex flex-wrap w-1/4 text-xl bg-gray-200">
            연락처
          </label>
          <span className="p-4 flex flex-wrap w-3/4 justify-center text-xl bg-gray-100 font-bold">
            {myInfo?.phoneNumber}
          </span>
        </div>
        {/* 성별 */}
        <div className="flex flex-row border-b border-gray-400">
          <label className="p-4 flex flex-wrap w-1/4 text-xl bg-gray-200">
            성별
          </label>
          <span className="p-4 flex flex-wrap w-3/4 justify-center bg-gray-100 text-xl font-bold">
            {myInfo?.gender}
          </span>
        </div>
        {/* 생일 */}
        <div className="flex flex-row border-b border-gray-400">
          <label className="p-4 flex flex-wrap w-1/4 text-xl bg-gray-200">
            생일
          </label>
          <span className="p-4 flex flex-wrap w-3/4 justify-center bg-gray-100 text-xl font-bold">
            {myInfo?.birthday}
          </span>
        </div>
        {/* 주소 */}
        <div className="flex flex-row border-b-2 border-gray-400">
          <label className="p-4 flex flex-wrap w-1/4 text-xl bg-gray-200">
            주소
          </label>
          <span className="p-4 flex flex-wrap w-3/4 justify-center bg-gray-100 text-xl font-bold">
            {myInfo?.address}
          </span>
        </div>
      </div>
      {/* 버튼 */}
      <div className="w-10/12 flex justify-between mx-auto mt-10">
        {/* 수정하기 */}
        <button
          className="m-2 p-2 bg-blue-200 rounded-xl hover:bg-blue-300 font-semibold"
          onclick={() => navigate("/mypage/edit")}
        >
          회원 정보 수정
        </button>
        {/* 비밀번호 변경 */}
        <button className="m-2 p-2 bg-blue-200 rounded-xl hover:bg-blue-300 font-semibold">
          비밀번호 변경
        </button>
        {/* 회원탈퇴 */}
        <button
          className="m-2 p-2 bg-blue-200 rounded-xl hover:bg-blue-300 font-semibold"
          onClick={handleDelete}
        >
          회원 탈퇴
        </button>
      </div>
    </div>
  );
};

export default Info;
