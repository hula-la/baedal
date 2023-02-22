import React from "react";
import styled from "styled-components";
import Adress from "./components/Adress";
import Menu from "./components/Menu";
import Search from "./components/Search";

const mainPage = () => {
  return (
    <div>
      <Search />
      <Adress />
      <Menu />
    </div>
  );
};

export default mainPage;
