import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import { getPopupList } from "../../api/popupApi";

const PopupListComponent = () => {
  const [list, setList] = useState([]);

  const navigate = useNavigate();

  useEffect(() => {
    getPopupList()
      .then((data) => {
        setList(data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);
  return (
    <div className="max-w-2xl mx-auto mt-10 p-6 bg-white shadow-lg">
      <h1 className="text-3xl font-bold">리스트</h1>
      <hr className="p-2"></hr>
      <div className="grid grid-cols-2 gap-4">
        {list.map((popup) => (
          <div
            key={popup.id}
            className="w-[320px] h-[180px] border border-gray-500 rounded-2xl hover:cursor-pointer transition p-2"
            onClick={() => navigate(`../${popup.id}`)}
          >
            <div className="text-xl font-bold">{popup.title}</div>
            <div>{popup.address}</div>
            <span>{popup.startDate}</span>
            <span> ~ </span>
            <span>{popup.endDate}</span>
          </div>
        ))}
      </div>
    </div>
  );
};
export default PopupListComponent;
