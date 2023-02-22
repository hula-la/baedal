import React, { lazy } from "react";
import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

const Home = lazy(() => import("./pages/main/mainPage"));

function App() {
  return (
    <Router>
      <Routes>
        <Route path="" element={<Home />} />
      </Routes>
    </Router>
  );
}

export default App;
