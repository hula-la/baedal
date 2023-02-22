import React, { Suspense } from "react";
import { ErrorBoundary } from "react-error-boundary";
import styled from "styled-components";
import Adress from "./components/Adress";
import Menu from "./components/Menu";
import Search from "./components/Search";

const mainPage = () => {
  return (
    <div>
      <Search />
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
