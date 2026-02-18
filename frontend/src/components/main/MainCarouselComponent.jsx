import { useState, useEffect } from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import { Navigation, Pagination, Autoplay } from "swiper/modules";
import { getPopupList } from "../../api/popupApi";
import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import { API_SERVER_HOST } from "../../api/apiConfig";

const MainCarouselComponent = () => {
  const [banners, setBanners] = useState([]);

  useEffect(() => {
    getPopupList()
      .then((data) => {
        setBanners(data.slice(0, 5));
      })
      .catch((error) =>
        console.error("배너 데이터 불러오는데 실패했습니다", error),
      );
  }, []);

  if (banners.length === 0) {
    return <div className="h-[400px] bg-gray-100 animate-pulse"></div>;
  }

  return (
    <div className="w-full py-10 bg-gray-50 overflow-hidden">
      <Swiper
        modules={[Navigation, Pagination, Autoplay]}
        spaceBetween={30}
        slidesPerView={1.2}
        centeredSlides={true}
        loop={true}
        pagination={{ clickable: true }}
        autoplay={{ delay: 3000, disableOnInteraction: false }}
        breakpoints={{
          768: { slidesPerView: 1.5 },
          1024: { slidesPerView: 2.2 },
        }}
        className="w-full h-[360px]"
      >
        {banners.map((popup) => (
          <SwiperSlide
            key={popup.id}
            className="transition-all duration-500 py-10"
          >
            {({ isActive }) => (
              <div
                className={`relative w-full h-[350px] md:h-[450px] rounded-3xl overflow-hidden shadow-2xl transition-transform duration-500 ${isActive ? "scale-105 z-10" : "scale-90 opacity-50 text-gray-400"}`}
              >
                {/* 이미지 */}
                <img
                  src={`${API_SERVER_HOST}${popup.thumbnailUrl}`}
                  alt={popup.title}
                  className="w-full h-full object-cover"
                ></img>

                {/* 이미지 위 검정 그라데이션 */}
                <div className="absolute inset-0 bg-linear-to-t from-black/80 via-black/20 to-transparent"></div>
              </div>
            )}
          </SwiperSlide>
        ))}
      </Swiper>
    </div>
  );
};

export default MainCarouselComponent;
