import { Suspense } from "react";
import { ErrorBoundary } from "react-error-boundary";
import StoreList from "./components/storeList";

const StoreListPage = () => {
  return (
    <div>
      <div>
        업체 리스트
      </div>
      <ErrorBoundary fallback={<div>에러요</div>}>
        <Suspense fallback={<div>로딩중</div>}>
          <StoreList />
        </Suspense>
      </ErrorBoundary>
    </div>
  )
}

export default StoreListPage;