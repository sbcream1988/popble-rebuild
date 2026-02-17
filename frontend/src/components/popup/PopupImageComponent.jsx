import { useParams } from "react-router";

import { useEffect, useState } from "react";
import { getPopup } from "../../api/popupApi";
import { API_SERVER_HOST } from "../../api/apiConfig";

const PopupImageComponent = () => {
  const { id } = useParams();
  const [images, setImages] = useState([]);

  useEffect(() => {
    const fetchPopup = async () => {
      try {
        const data = await getPopup(id);
        setImages(data.images || []);
        console.log("데이터", data);
      } catch (error) {
        console.error("이미지 조회 실패", error);
      }
    };
    fetchPopup();
  }, [id]);

  if (images.length === 0) {
    return <div>등록된 이미지가 없습니다</div>;
  }
  return (
    <div>
      <h1>이미지 상세보기</h1>
      <div>
        {images.map((img, index) => (
          <div key={index}>
            <img
              src={`${API_SERVER_HOST}${img.accessUrl}`}
              alt={`팝업 이미지 ${index + 1}`}
              style={{ width: "100%", marginBottom: "20px" }}
            ></img>
          </div>
        ))}
      </div>
    </div>
  );
};

export default PopupImageComponent;
