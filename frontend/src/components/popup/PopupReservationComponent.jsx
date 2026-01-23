import { useParams } from "react-router";
import StepConfirm from "../reservation/StepConfirm";
import StepSelectSlot from "../reservation/StepSelectSlot";
import StepUserInfo from "../reservation/StepUserInfo";
import { useState } from "react";

const PopupReservationComponent = () => {
  const { id } = useParams();
  const popupId = Number(id);
  const [step, setStep] = useState(1);

  const [reservationData, setReservationData] = useState({
    popupId,
    popupReservationId: null,
    date: "",
    startTime: "",
    endTime: "",
    count: 1,
    reserverName: "",
    phoneNumber: "",
  });

  return (
    <div>
      {/* 날짜 인원 선택 */}
      {step === 1 && (
        <StepSelectSlot
          data={reservationData}
          setData={setReservationData}
          onNext={() => setStep(2)}
        ></StepSelectSlot>
      )}
      {/* 예약자 정보 입력 */}
      {step === 2 && (
        <StepUserInfo
          data={reservationData}
          setData={setReservationData}
          onPrev={() => setStep(1)}
          onNext={() => setStep(3)}
        ></StepUserInfo>
      )}
      {/* 확인 */}
      {step === 3 && (
        <StepConfirm
          data={reservationData}
          setData={setReservationData}
          onPrev={() => setStep(2)}
        ></StepConfirm>
      )}
    </div>
  );
};

export default PopupReservationComponent;
