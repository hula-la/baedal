import React, { lazy } from "react";
import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import BasketPage from "./pages/basket/basketPage";
import OrderPage from "./pages/order/orderPage";
import StoreDetailPage from "./pages/storeDetail/storeDetailPage";
import MenuDetail from "./pages/menuDetail/components/MenuDetail";
import MakeReviewPage from "./pages/makeReview/makeReviewPage";
import MyPage from "./pages/myPage/myPage";

const Home = lazy(() => import("./pages/main/mainPage"));

function App() {
  return (
    <div>
      <nav>네브바</nav>
      <Router>
        <Routes>
          <Route path="" element={<Home />} />
          <Route path="/basket" element={<BasketPage />}></Route>
          <Route path="/order" element={<OrderPage />}></Route>
          <Route path="/storeDetail" element={<StoreDetailPage />}></Route>
          <Route path="/storeDetail/:id" element={<MenuDetail />}></Route>
          <Route path="/review" element={<MakeReviewPage />}></Route>
          <Route path="/myPage/*" element={<MyPage />}></Route>
        </Routes>
      </Router>
    </div>
  );
}

export default App;
