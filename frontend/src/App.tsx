import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import BasketPage from "./pages/basket/basketPage";
import OrderPage from "./pages/order/orderPage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/basket" element={<BasketPage />}></Route>
        <Route path="/order" element={<OrderPage />}></Route>
      </Routes>
    </Router>
  );
}

export default App;
