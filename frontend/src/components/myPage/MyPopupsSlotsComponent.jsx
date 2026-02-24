import { useState, useEffect } from "react";
import { createSlot, getSlotsByPopup } from "../../api/slotApi";
import { useParams } from "react-router";

const MyPopupsSlotsComponent = () => {
  const { popupId } = useParams();

  const [date, setDate] = useState("");
  const [startTime, setStartTime] = useState("");
  const [endTime, setEndTime] = useState("");
  const [maxCount, setMaxCount] = useState("");
  const [slots, setSlots] = useState([]);

  //슬롯 목록 불러오기
  const fetchSlots = async () => {
    try {
      const data = await getSlotsByPopup(popupId);
      setSlots(data);
    } catch (e) {
      console.error("슬롯 조회 실패", e);
      throw e;
    }
  };

  useEffect(() => {
    fetchSlots();
  }, [popupId]);

  //슬롯 생성
  const handleCreate = async () => {
    if (!date || !startTime || !endTime) {
      alert("모든 값을 입력하세요");
      return;
    }

    try {
      await createSlot(popupId, {
        date,
        startTime,
        endTime,
        maxCount: Number(maxCount),
      });
      setDate("");
      setStartTime("");
      setEndTime("");
      setMaxCount("");
      fetchSlots(); // 생성 후 재조회
    } catch (e) {
      console.error("슬롯 생성 실패", e);
      throw e;
    }
  };

  return (
    <div>
      {/* 슬롯 생성 */}
      <div>
        <div className="m-4 flex flex-row gap-4 justify-between">
          {/* 날짜 */}
          <div className="flex flex-col">
            <label className="m-2 p-2 font-bold">날짜</label>
            <input
              className="p-2 border border-gray-200 rounded-2xl"
              type="date"
              value={date}
              onChange={(e) => setDate(e.target.value)}
            ></input>
          </div>
          {/* 시작시간 */}
          <div className="flex flex-col">
            <label className="m-2 p-2 font-bold">시작시간</label>
            <input
              className="p-2 border border-gray-200 rounded-2xl"
              type="time"
              value={startTime}
              step="600"
              onChange={(e) => setStartTime(e.target.value)}
            ></input>
          </div>
          {/* 종료시간 */}
          <div className="flex flex-col">
            <label className="m-2 p-2 font-bold">종료시간</label>
            <input
              className="p-2 border border-gray-200 rounded-2xl"
              type="time"
              value={endTime}
              step="600"
              onChange={(e) => setEndTime(e.target.value)}
            ></input>
          </div>
          {/* 최대인원 */}
          <div className="flex flex-col">
            <label className="m-2 p-2 font-bold">최대인원</label>
            <input
              className="text-end"
              type="number"
              value={maxCount}
              onChange={(e) => setMaxCount(e.target.value)}
            ></input>
          </div>
          <button
            className="m-2 p-2 w-15 h-15 bg-blue-200 rounded-full"
            onClick={handleCreate}
          >
            추가
          </button>
        </div>
      </div>
      <hr></hr>
      {/* 슬롯 목록 */}
      <div className="w-40 m-2 p-2 mx-auto font-bold">슬롯 목록</div>
      <div>
        <table className="w-full table-fixed">
          <thead className="bg-blue-100">
            <tr>
              <th className="p-2 rounded-tl-2xl">날짜</th>
              <th className="p-2">시간</th>
              <th className="p-2">인원</th>
              <th className="p-2 rounded-tr-2xl">슬롯 삭제</th>
            </tr>
          </thead>
          <tbody>
            {slots.map((slot) => (
              <tr key={slot.id}>
                <td className="p-2 border-b border-gray-200">{slot.date}</td>
                <td className="p-2 border-b border-gray-200">
                  {slot.startTime} ~ {slot.endTime}
                </td>
                <td className="p-2 border-b border-gray-200">
                  {slot.currentCount}/{slot.maxCount}
                </td>
                <td className="p-2 border-b border-gray-200">
                  <button
                    className="p-2 m-2 bg-red-200 rounded-full"
                    onClick={() => {
                      console.log("삭제");
                    }}
                  >
                    -
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default MyPopupsSlotsComponent;
