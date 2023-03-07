import React, { Suspense } from "react";
import { ErrorBoundary } from "react-error-boundary";
import styled from "styled-components";
import Adress from "./components/Adress";
import Menu from "./components/Menu";
import Search from "./components/Search";

const SearchWrapper = styled.div`
  width: 100vw;
  height: 70vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background-image: url("img/main/food.jpg");
  background-size: auto, 70vh;

`

const mainPage = () => {
  return (
    <div>
      <SearchWrapper>
        <Search />
      </SearchWrapper>
      <Adress />
      <ErrorBoundary fallback={<div>실패요</div>}>
        <Suspense fallback={<div>로딩중입니당...</div>}>
          <Menu />
        </Suspense>
      </ErrorBoundary>
    </div>
  );
};

export default mainPage;
