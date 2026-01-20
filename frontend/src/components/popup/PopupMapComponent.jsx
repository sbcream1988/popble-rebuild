import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { getPopup } from "../../api/popupApi";

const PopupMapComponent = () => {
  const { id } = useParams();
  const [popup, setPopup] = useState(null);

  useEffect(() => {
    getPopup(id)
      .then((data) => {
        setPopup(data);
      })
      .catch((error) => console.error(error));
  }, [id]);

  if (!popup) return <div>지도 로딩중...</div>;

  return (
    <div>
      <h1>지도</h1>
      <div>
        <div>지도 위치</div>
        <div>{popup.address}</div>
      </div>
    </div>
  );
};

export default PopupMapComponent;
