import { Suspense } from "react";
import { ErrorBoundary } from "react-error-boundary";
import { Routes, Route } from "react-router-dom";
import MyPageDashboard from "./components/myPageDashboard";
import OrderListPage from "./components/orderListPage";
import LikeStorePage from "./components/likeStorePage";
import MyReviewPage from "./components/myReviewPage";
import ProfilePage from "./components/profilePage";
import MyPageSideBar from "./components/myPageSideBar";

const MyPage = () => {
  return (
    <div>
      <main>
        <ErrorBoundary fallback={<div>실패</div>}>
          <Suspense fallback={<div>로딩중</div>}>
            <Routes>
              <Route path="" element={<MyPageDashboard />}></Route>
              <Route path="orderList" element={<OrderListPage />}></Route>
              <Route path="likeStore" element={<LikeStorePage />}></Route>
              <Route path="myReview" element={<MyReviewPage />}></Route>
              <Route path="profile" element={<ProfilePage />}></Route>
              <Route path="*" element={<div>오류</div>}></Route>
            </Routes>
          </Suspense>
        </ErrorBoundary>
      </main>
      <div>
        <MyPageSideBar />
      </div>
    </div>
  )
}

export default MyPage;