import React, { Suspense } from "react";
import { ErrorBoundary } from "react-error-boundary";
import { Link } from "react-router-dom";
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
      <div>
        <Link to="/basket">
          <button>장바구니로</button>
        </Link>
      </div>
    </div>
  )
}

export default storeDetailPage;