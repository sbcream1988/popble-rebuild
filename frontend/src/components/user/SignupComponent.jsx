import DatePicker from "react-datepicker";
import { signup } from "../../api/userApi";
import { Controller, useForm } from "react-hook-form";
import "react-datepicker/dist/react-datepicker.css";
import { useNavigate } from "react-router";

function SignupComponent() {
  const navigate = useNavigate();

  const {
    register,
    handleSubmit,
    formState: { errors },
    // watch,
    control,
  } = useForm({
    defaultValues: {
      birthday: null, // null로 초기화
    },
  });

  const onSubmit = async (data) => {
    //비밀번호 체크
    if (data.password !== data.passwordCheck) {
      alert("비밀번호가 일치하지 않습니다");
      return;
    }

    try {
      const result = await signup(data);
      console.log("회원가입 성공", result);
      alert("회원가입 성공");
      navigate("/auth/login");
    } catch (e) {
      console.error("회원가입 실패", e);
      alert("회원가입 실패");
    }
  };

  return (
    <div className="w-full min-h-screen flex flex-col justify-center items-center">
      <h1 className="text-3xl font-bold mb-10">회원가입</h1>

      <form
        onSubmit={handleSubmit(onSubmit)}
        className="m-4 p-4 w-full max-w-lg flex flex-col items-center gap-6 border border-gray-200 rounded-3xl"
      >
        {/* 이메일 */}
        <InputField
          label="이메일"
          name="email"
          type="email"
          placeholder="이메일을 입력하세요"
          register={register}
          required={true}
        />
        {errors.email && (
          <p className="text-red-600 text-sm text-center">
            이메일은 필수 입력 사항입니다
          </p>
        )}
        {/* 비밀번호 */}
        <InputField
          label="비밀번호"
          name="password"
          type="password"
          placeholder="비밀번호를 입력하세요"
          register={register}
          required={true}
        />
        {errors.password && (
          <p className="text-red-600 text-sm text-center">
            비밀번호는 필수 입력 사항입니다
          </p>
        )}

        {/* 비밀번호 재확인 */}
        <InputField
          label="비밀번호 확인"
          name="passwordCheck"
          type="password"
          placeholder="비밀번호를 다시 입력하세요"
          register={register}
          required={true}
        />
        {errors.passwordCheck && (
          <p className="text-red-600 text-sm text-center">
            비밀번호 확인은 필수 입력 사항입니다
          </p>
        )}
        {/* 닉네임 */}
        <InputField label="닉네임" name="nickname" register={register} />
        {/* 생일 */}
        <div className="flex flex-col gap-1 w-full max-w-md items-start">
          <label className="font-bold">생일</label>
          <Controller
            control={control}
            name="birthday"
            render={({ field }) => (
              <DatePicker
                className="w-full p-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400"
                placeholderText="생일 선택"
                selected={field.value}
                onChange={field.onChange}
                dateFormat="yyyy-MM-dd"
                maxDate={new Date()}
                showYearDropdown
                showMonthDropdown
                dropdownMode="select"
              />
            )}
          />
        </div>
        {/* 성별 */}

        {/* 연락처 */}
        <InputField
          label={"연락처"}
          name={"phoneNumber"}
          type="tel"
          register={register}
        />
        {/* 주소 */}
        <InputField
          label={"주소"}
          name={"address"}
          type="text"
          register={register}
        />
        {/* 회원 가입 버튼 */}
        <button
          type="submit"
          className="mt-10 p-3 rounded-4xl bg-blue-200 cursor-pointer "
        >
          회원가입
        </button>
      </form>
    </div>
  );
}

export default SignupComponent;

// 입력 칸
function InputField({
  label,
  type = "text",
  placeholder,
  register,
  name,
  error,
  required,
}) {
  return (
    <div className="flex flex-col gap-1 w-full max-w-md items-start">
      <label className="font-bold">{label}</label>
      <input
        className="w-full p-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400"
        type={type}
        placeholder={placeholder}
        {...register(name, { required })}
      ></input>
      {error && <p className="text-red-500 text-xs">{error.message}</p>}
    </div>
  );
}
