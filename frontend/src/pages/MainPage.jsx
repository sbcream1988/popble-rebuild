import MainCarouselComponent from "../components/main/MainCarouselComponent";
import BasicLayout from "../layouts/BasicLayout";

function MainPage() {
  return (
    <BasicLayout>
      <div className="text-9xl flex justify-center items-center">
        <MainCarouselComponent></MainCarouselComponent>
      </div>
    </BasicLayout>
  );
}

export default MainPage;
