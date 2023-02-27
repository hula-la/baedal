import { Link } from "react-router-dom";

const MyPageSideBar = () => {
  return (
    <div>
      <ul>
        <Link to="/myPage"><li>대시보드</li></Link>
        <Link to="/myPage/orderList"><li>주문내역</li></Link>
        <Link to="/myPage/likeStore"><li>찜</li></Link>
        <Link to="/myPage/myReview"><li>리뷰관리</li></Link>
        <li>회원탈퇴</li>
        <Link to="/myPage/profile"><li>프로필</li></Link>
        <li>로그아웃</li>
      </ul>
    </div>
  )
}

export default MyPageSideBar;