import React, { Suspense } from "react";
import { ErrorBoundary } from "react-error-boundary";
import StoreDetail from "./components/StoreDetail";
import StoreHeader from "./components/StoreHeader";
import StoreInfo from "./components/StoreInfo";

const storeDetailPage = () => {
  return (
    <div>
      <header>
        <ErrorBoundary fallback={<div>실패요</div>}>
          <Suspense fallback={<div>로딩중</div>}>
            <StoreHeader />
          </Suspense>
        </ErrorBoundary>
      </header>
      <main>
        <div>
          <ErrorBoundary fallback={<div>실패요</div>}>
            <Suspense fallback={<div>로딩중</div>}>
              <StoreInfo />
            </Suspense>
          </ErrorBoundary>
        </div>
        <StoreDetail />
      </main>
    </div>
  )
}

export default storeDetailPage;