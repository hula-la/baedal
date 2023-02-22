import React, { Suspense } from "react";
import { ErrorBoundary } from "react-error-boundary";
import MenuDetail from "./components/MenuDetail";

const menuDetailPage = () => {
  return (
    <div>
      <ErrorBoundary fallback={<div>실패여</div>}>
        <Suspense fallback={<div>로딩중</div>}>
          <MenuDetail />
        </Suspense>
      </ErrorBoundary>
    </div>
  )
}

export default menuDetailPage;