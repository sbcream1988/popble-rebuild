import { useState } from "react";
import { createPopup } from "../../api/popupApi";
import { useNavigate } from "react-router";

const PopupCreateComponent = () => {
  const [form, setForm] = useState({
    title: "",
    description: "",
    address: "",
    startDate: "",
    endDate: "",
    price: 0,
    maxCapacity: 0,
  });

  const [images, setImages] = useState([]);

  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleImage = (e) => {
    setImages(Array.from(e.target.files));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const requestDTO = {
      ...form,
      price: Number(form.price),
      maxCapacity: Number(form.maxCapacity),
    };

    try {
      const popup = await createPopup(requestDTO, images);
      alert("팝업스토어 등록이 완료되었습니다");
      navigate(`/api/popup/${popup.id}/slots`);
    } catch (error) {
      console.error(error);
      alert("팝업스토어 등록 중 오류가 발생했습니다");
    }
  };
  return (
    <div className="max-w-2xl mx-auto mt-10 p-6 bg-white shadow rounded-lg">
      <h1 className="text-3xl p-2 m-2 text-left">팝업스토어 등록</h1>
      <hr className="p-2"></hr>
      <form className="space-y-4 w-11/12" onSubmit={handleSubmit}>
        <div className="font-bold mb-2">
          <label className="p-2 m-2 block text-left">팝업스토어 이름</label>
          <input
            className="p-2 m-2 w-full border border-gray-500 rounded-xl"
            type="text"
            name="title"
            value={form.title}
            onChange={handleChange}
            placeholder="팝업이름을 입력해주세요"
          ></input>
        </div>
        <div className="font-bold mb-2">
          <label className="p-2 m-2 block text-left">팝업스토어 소개</label>
          <textarea
            className="p-2 m-2 w-full border border-gray-500 rounded-xl"
            rows={20}
            name="description"
            value={form.description}
            onChange={handleChange}
            placeholder="팝업스토어를 소개해주세요"
          ></textarea>
        </div>
        <div className="font-bold mb-2">
          <label className="p-2 m-2 block text-left">주소</label>
          <input
            className="p-2 m-2 w-full border border-gray-500 rounded-xl"
            type="text"
            name="address"
            value={form.address}
            onChange={handleChange}
            placeholder="주소를 입력해주세요"
          ></input>
        </div>
        <div className="grid grid-cols-2 gap-4">
          <div>
            <label className="p-2 m-2 block text-left font-bold">시작일</label>
            <input
              className="m-2 p-2 border border-gray-500 rounded-xl w-full"
              type="date"
              name="startDate"
              value={form.startDate}
              onChange={handleChange}
            ></input>
          </div>
          <div>
            <label className="p-2 m-2 block text-left font-bold">종료일</label>
            <input
              className="m-2 p-2 border border-gray-500 rounded-xl text-right w-full"
              type="date"
              name="endDate"
              value={form.endDate}
              onChange={handleChange}
            ></input>
          </div>
        </div>
        <div className="grid grid-cols-2 gap-4">
          <div className="font-bold">
            <label className="m-2 p-2 block text-left">가격</label>
            <input
              className="m-2 p-2 border border-gray-500 rounded-xl text-right"
              type="number"
              name="price"
              value={form.price}
              onChange={handleChange}
            ></input>
            <span>원</span>
          </div>
          <div className="font-bold">
            <label className="m-2 p-2 block text-left">최대 인원</label>
            <input
              className="m-2 p-2 border border-gray-400 rounded-xl text-right"
              type="number"
              name="maxCapacity"
              value={form.maxCapacity}
              onChange={handleChange}
            ></input>
            <span>명</span>
          </div>
        </div>
        <div>
          <label>팝업이미지</label>
          <input
            type="file"
            name="images"
            multiple
            onChange={handleImage}
          ></input>
        </div>
        <hr className="p-2"></hr>
        {/* 등록 버튼 */}
        <button
          className="m-2 p-2 w-1/4 rounded-2xl bg-blue-300 font-bold hover:cursor-pointer hover:bg-blue-400"
          type="submit"
        >
          등록하기
        </button>
      </form>
    </div>
  );
};

export default PopupCreateComponent;
