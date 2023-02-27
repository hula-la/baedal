import { useNavigate } from "react-router-dom";

const MyPageDashboard = () => {

  const navigate = useNavigate()

  return (
    <div>
      <div>안녕하세요~</div>
      <div>
        <button onClick={() => {navigate("orderList")}}>주문내역</button>
        <button onClick={() => {navigate("likeStore")}}>찜</button>
        <button onClick={() => {navigate("myReview")}}>리뷰관리</button>
        <button>회원탈퇴</button>
        <button onClick={() => {navigate("profile")}}>프로필</button>
        <button>로그아웃</button>
      </div>
    </div>
  )
}

export default MyPageDashboard