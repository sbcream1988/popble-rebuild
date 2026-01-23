import { createReserve } from "../../api/reservationApi";

const StepConfirm = ({ data, onPrev }) => {
  const handleSubmit = async () => {
    try {
      await createReserve(data);
      alert("예약 완료");
    } catch (error) {
      alert("예약 실패");
      console.error(error);
      console;
    }
  };
  return (
    <div>
      <h3 className="text-3xl">예약 확인</h3>

      <p>슬롯 ID: {data.popupReservationSlotId}</p>
      <p>인원:{data.count}</p>
      <p>예약자명:{data.reserverName}</p>
      <p>전화번호:{data.phoneNumber}</p>

      <button onClick={onPrev}>이전</button>
      <button onClick={handleSubmit}>예약하기</button>
    </div>
  );
};

export default StepConfirm;
