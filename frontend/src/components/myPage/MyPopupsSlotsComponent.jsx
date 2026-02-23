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
      <h1>슬롯관리</h1>
      {/* 슬롯 생성 */}
      <div>
        {/* 날짜 */}
        <input
          type="date"
          value={date}
          onChange={(e) => setDate(e.target.value)}
        ></input>
        {/* 시작시간 */}
        <input
          type="time"
          value={startTime}
          step="600"
          onChange={(e) => setStartTime(e.target.value)}
        ></input>
        {/* 종료시간 */}
        <input
          type="time"
          value={endTime}
          step="600"
          onChange={(e) => setEndTime(e.target.value)}
        ></input>
        {/* 최대인원 */}
        <input
          type="number"
          value={maxCount}
          onChange={(e) => setMaxCount(e.target.value)}
        ></input>
        <button onClick={handleCreate}>슬롯 생성</button>
      </div>
      <hr></hr>
      {slots.map((slot) => (
        <div key={slot.id}>
          <div>{slot.date}</div>
          <div>
            {slot.startTime} ~ {slot.endTime}
          </div>
          <div>
            {slot.currentCount}/{slot.maxCount}
          </div>
        </div>
      ))}
    </div>
  );
};

export default MyPopupsSlotsComponent;
