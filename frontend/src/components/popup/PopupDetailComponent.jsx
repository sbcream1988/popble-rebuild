import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { getPopup } from "../../api/popupApi";

const PopupDetailComponent = () => {
  const { id } = useParams();
  const [popup, setPopup] = useState(null);

  useEffect(() => {
    getPopup(id)
      .then((data) => {
        setPopup(data);
      })
      .catch((error) => console.error(error));
  }, [id]);

  if (!popup) return <div>...로딩중</div>;

  return (
    <div>
      <div className="text-4xl font-bold">{popup.title}</div>
      <div>
        <span>{popup.startDate}</span>
        <span> ~ </span>
        <span>{popup.endDate}</span>
      </div>
      <div>{popup.address}</div>
      <div>{popup.description}</div>
    </div>
  );
};

export default PopupDetailComponent;
