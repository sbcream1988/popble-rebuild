import { useEffect, useState } from "react";
import { getMyInfo, updateMyInfo } from "../../api/myPageApi";
import { useNavigate } from "react-router";

const InfoUpdateComponent = () => {
  const [form, setForm] = useState({
    email: "",
    nickname: "",
    phoneNumber: "",
    gender: "",
    birthday: "",
    address: "",
  });

  const navigate = useNavigate();

  useEffect(() => {
    getMyInfo().then((data) => {
      setForm({
        email: data.email,
        nickname: data.nickname,
        phoneNumber: data.phoneNumber,
        gender: data.gender,
        birthday: data.birthday,
        address: data.address,
      });
    });
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await updateMyInfo(form);
      alert("수정 완료");
      navigate("/mypage/info");
    } catch (error) {
      console.error(error);
      alert("수정 실패");
    }
  };

  return (
    <div>
      <h1>업데이트 페이지</h1>

      <form className="w-11/12 mx-auto" onSubmit={handleSubmit}>
        {/* 아이디 */}
        <div className="flex flex-row border-b border-b-gray-400 border-t-2 border-t-gray-400">
          <label className="p-4 flex flex-wrap w-1/4 text-xl bg-gray-200">
            아이디(이메일)
          </label>
          <input
            className="p-4 flex flex-wrap w-3/4 justify-center bg-gray-100 text-xl font-bold"
            name="email"
            value={form.email}
            disabled={true}
          ></input>
        </div>
        {/* 닉네임 */}
        <div className="flex flex-row border-b border-gray-400">
          <label className="p-4 flex flex-wrap w-1/4 text-xl bg-gray-200">
            닉네임
          </label>
          <input
            className="p-4 flex flex-wrap w-3/4 justify-center bg-gray-100 text-xl font-bold"
            name="nickname"
            value={form.nickname}
            onChange={handleChange}
          ></input>
        </div>
        {/* 전화번호 */}
        <div className="flex flex-row border-b border-gray-400">
          <label className="p-4 flex flex-wrap w-1/4 text-xl bg-gray-200">
            전화번호
          </label>
          <input
            className="p-4 flex flex-wrap w-3/4 justify-center bg-gray-100 text-xl font-bold"
            name="phoneNumber"
            value={form.phoneNumber}
            onChange={handleChange}
          ></input>
        </div>
        {/* 성별 */}
        <div className="flex flex-row border-b border-gray-400">
          <label className="p-4 flex flex-wrap w-1/4 text-xl bg-gray-200">
            성별
          </label>
          <input
            className="p-4 flex flex-wrap w-3/4 justify-center bg-gray-100 text-xl font-bold"
            value={form.gender}
            disabled={true}
          ></input>
        </div>
        {/* 생일 */}
        <div className="flex flex-row border-b border-gray-400">
          <label className="p-4 flex flex-wrap w-1/4 text-xl bg-gray-200">
            생일
          </label>
          <input
            className="p-4 flex flex-wrap w-3/4 justify-center bg-gray-100 text-xl font-bold "
            value={form.birthday}
            disabled={true}
          ></input>
        </div>
        {/* 주소 */}
        <div className="flex flex-row border-b border-gray-400">
          <label className="p-4 flex flex-wrap w-1/4 text-xl bg-gray-200">
            주소
          </label>
          <input
            className="p-4 flex flex-wrap w-3/4 justify-center bg-gray-100 text-xl font-bold"
            name="address"
            value={form.address}
            onChange={handleChange}
          ></input>
        </div>
        {/* 버튼 */}
        <div className="w-10/12 flex justify-between mx-auto mt-10">
          <button
            type="button"
            className="m-2 p-2 bg-blue-200 rounded-xl hover:bg-blue-300 font-semibold"
            onClick={() => navigate("/mypage/info")}
          >
            돌아가기
          </button>
          <button
            className="m-2 p-2 bg-blue-200 rounded-xl hover:bg-blue-300 font-semibold"
            type="submit"
          >
            수정하기
          </button>
        </div>
      </form>
    </div>
  );
};

export default InfoUpdateComponent;
