const StepUserInfo = ({ data, setData, onPrev, onNext }) => {
  return (
    <div className="flex flex-col justify-center">
      <h3 className="text-3xl"> 예약자 정보</h3>
      <p className="text-xl font-bold">날짜 : {data.date}</p>
      <p className="text-xl font-bold">
        시간 : {data.startTime} ~ {data.endTime}
      </p>
      <p className="text-xl font-bold">인원 : {data.count} 명</p>
      <div className="gap-4 p-2 m-2">
        <div>
          <label className="p-2 font-bold text-xl">예약자</label>
          <input
            placeholder="예약자명"
            value={data.reserverName}
            onChange={(e) => setData({ ...data, reserverName: e.target.value })}
            className="border border-gray-500 p-2 rounded-2xl"
          ></input>
        </div>
        <div className="p-2 m-2 text-xl">
          <label className="p-2 font-bold text-xl">연락처</label>
          <input
            placeholder="연락처"
            value={data.phoneNumber}
            onChange={(e) => setData({ ...data, phoneNumber: e.target.value })}
            className="border border-gray-500 p-2 rounded-2xl"
          ></input>
        </div>
        <div></div>
        <div>
          <button
            onClick={onPrev}
            className="m-2 p-2 bg-blue-300 rounded-xl hover:bg-blue-400"
          >
            이전
          </button>
          <button
            onClick={onNext}
            disabled={!data.reserverName || !data.phoneNumber}
            className="m-2 p-2 bg-blue-300 rounded-xl hover:bg-blue-400"
          >
            다음
          </button>
        </div>
      </div>
    </div>
  );
};

export default StepUserInfo;
