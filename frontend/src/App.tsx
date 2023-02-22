import React, { lazy } from "react";
import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import BasketPage from "./pages/basket/basketPage";
import OrderPage from "./pages/order/orderPage";
import StoreDetailPage from "./pages/storeDetail/storeDetailPage";

const Home = lazy(() => import("./pages/main/mainPage"));

function App() {
  return (
    <Router>
      <Routes>
        <Route path="" element={<Home />} />
        <Route path="/basket" element={<BasketPage />}></Route>
        <Route path="/order" element={<OrderPage />}></Route>
        <Route path="/storeDetail" element={<StoreDetailPage />}></Route>
      </Routes>
    </Router>
  );
}

export default App;
