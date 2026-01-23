import { useEffect, useState } from "react";
import { getSlotsByPopup } from "../../api/slotApi";

const StepSelectSlot = ({ data, setData, onNext }) => {
  const [slots, setSlots] = useState([]);
  const [selectedDate, setSelectedDate] = useState(null);

  useEffect(() => {
    getSlotsByPopup(data.popupId).then(setSlots);
  }, [data.popupId]);

  //날짜 중복 제거
  const dates = [...new Set(slots.map((slot) => slot.date))];

  //선택한 날짜에 해당하는 슬롯만
  const filteredSlots = slots.filter((slot) => slot.date === selectedDate);
  return (
    <div>
      <h3 className="text-3xl">날짜/시간 선택</h3>
      {/* 날짜선택 */}
      <div className="mb-6">
        <h4 className="text-xl mb-2">날짜 선택</h4>
        <div className="grid grid-cols-3 gap-3">
          {dates.map((date) => (
            <button
              key={date}
              onClick={() => {
                setSelectedDate(date);
                setData({
                  ...data,
                  popupReservationSlotId: null,
                });
              }}
              className={`p-3 rounded-xl border${selectedDate === date ? "bg-blue-400" : "bg-white hover:bg-gray-100"}`}
            >
              {date}
            </button>
          ))}
        </div>
        {/* 시간 선택 */}
        {selectedDate && (
          <div className="mb-6">
            <h4 className="text-xl mb-2">시간 선택</h4>
            <div className="grid grid-cols-2 gap-3">
              {filteredSlots.map((slot) => (
                <label
                  key={slot.id}
                  className={`p-3 rounded-xl border cursor-pointer ${data.popupReservationSlotId === slot.id ? "bg-blue-200" : "hover:bg-gray-100"}`}
                >
                  <input
                    type="radio"
                    name="slot"
                    className="hidden"
                    onChange={() =>
                      setData({
                        ...data,
                        popupReservationSlotId: slot.id,
                        date: slot.date,
                        startTime: slot.startTime,
                        endTime: slot.endTime,
                      })
                    }
                  ></input>
                  <div className="text-center font-semibold">
                    {slot.startTime} ~ {slot.endTime}
                  </div>
                </label>
              ))}
            </div>
          </div>
        )}
      </div>
      {/* 인원선택 */}
      <div className="m-2 p-2">
        <label className="m-2 p-2">인원</label>
        <input
          className="m-2 p-2 border border-gray-300 rounded-xl text-end"
          type="number"
          min={1}
          value={data.count}
          onChange={(e) => setData({ ...data, count: Number(e.target.value) })}
        ></input>
        <span> 명</span>
      </div>
      <div>
        <button
          disabled={!data.popupReservationSlotId}
          onClick={onNext}
          className="m-2 p-2 bg-blue-300 rounded-xl hover:bg-blue-400 disabled:opacity-50"
        >
          다음
        </button>
      </div>
    </div>
  );
};

export default StepSelectSlot;
