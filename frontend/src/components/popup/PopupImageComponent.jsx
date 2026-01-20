import { useParams } from "react-router";
import { popupImageDummy } from "../../data/popupImageDummy";

const PopupImageComponent = () => {
  const { id } = useParams();
  const images = popupImageDummy[id] || [];

  if (images.length === 0) {
    return <div>등록된 이미지가 없습니다</div>;
  }
  return (
    <div>
      <h1>이미지 상세보기</h1>
      <div>
        {images.map((img, index) => (
          <div key={index}>
            <img src={img} alt="팝업 이미지"></img>
          </div>
        ))}
      </div>
    </div>
  );
};

export default PopupImageComponent;
