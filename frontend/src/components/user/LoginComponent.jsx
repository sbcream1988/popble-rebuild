import { useForm } from "react-hook-form";
import { Link, useNavigate } from "react-router";
import { login } from "../../api/AuthApi";
import { useDispatch } from "react-redux";
import { loginSuccess } from "../../store/authSlice";

function LoginComponent() {
  const {
    register,
    handleSubmit,
    formState: { errors },
    // control,
  } = useForm();

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const onSubmit = async (data) => {
    try {
      const loginResponse = await login(data.email, data.password);
      console.log("로그인 성공:", loginResponse);

      dispatch(
        loginSuccess({
          accessToken: loginResponse.accessToken,
          user: {
            userId: loginResponse.userId,
            nickname: loginResponse.nickname,
            email: loginResponse.email,
            role: loginResponse.role,
          },
        }),
      );

      navigate("/");
    } catch (e) {
      console.error("로그인실패:", e.response?.data || e.message);
      alert("로그인 실패: 이메일 또는 비밀번호를 확인해주세요");
    }
  };

  return (
    <div className="min-h-screen flex flex-col items-center justify-center">
      <form
        onSubmit={handleSubmit(onSubmit)}
        className="w-[720px] p-6 rounded space-y-4"
      >
        {/* 아이디 */}
        <div className="relative flex flex-col items-center">
          <input
            id="email"
            type="email"
            {...register("email", { required: "아이디를 입력하세요" })}
            className="peer w-[360px] border-b-2 border-gray-300 bg-transparent py-3 px-1 text-gray-900 placeholder-transparent focus:outline-none focus:border-blue-500"
            placeholder="아이디"
          />
          <label
            className="absolute w-[360px] left-1 top-3 text-gray-500 text-sm transition-all
                            peer-placeholder-shown:text-base peer-placeholder-shown:text-gray-400
                            peer-placeholder-shown:top-3 peer-focus:top-0 peer-focus:text-xs peer-focus:text-blue-500"
          >
            아이디
          </label>
          {errors.email && (
            <p className="text-red-500 text-sm mt-1">{errors.email.message}</p>
          )}
        </div>

        {/* 비밀번호 */}
        <div className="relative flex flex-col items-center">
          <input
            className="peer w-[360px] border-b-2 border-gray-300 bg-transparent py-3 px-1 text-gray-900 placeholder-transparent focus:outline-none focus:border-blue-500"
            id="password"
            type="password"
            {...register("password", { required: "비밀번호를 입력하세요" })}
          />
          <label
            className="absolute w-[360px] left-1 top-3 text-gray-500 text-sm transition-all 
                            peer-placeholder-shown:text-base peer-placeholder-shown:text-gray-400 
                            peer-placeholder-shown:top-3 peer-focus:top-0 peer-focus:text-xs peer-focus:text-blue-500"
          >
            비밀번호
          </label>
          {errors.password && (
            <p className="text-red-500 text-sm mt-1">
              {errors.password.message}
            </p>
          )}
        </div>

        {/* 로그인 버튼 */}
        <button
          type="submit"
          className="w-[360px] p-2 rounded-2xl text-xl tracking-widest bg-purple-400 hover:bg-purple-500 border-0 border-purple-500 text-white transition duration-200"
        >
          로 그 인
        </button>
      </form>

      {/* 아이디/비밀번호 찾기 & 회원가입 */}
      <div className="w-[360px] flex flex-row justify-between">
        <Link to="/user" className="">
          아이디 / 비밀번호 찾기
        </Link>
        <Link to="/user/signup" className="">
          회원가입
        </Link>
      </div>

      {/* 구분선 */}
      <div className="flex items-center w-[360px] mt-3">
        <div className="grow border-t border-gray-200 mr-3"></div>
        <span className="text-gray-400">또는</span>
        <div className="grow border-t border-gray-200 ml-3"></div>
      </div>

      {/* 소셜 로그인 */}
    </div>
  );
}

export default LoginComponent;
