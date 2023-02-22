import { ErrorBoundary } from "react-error-boundary";
import { Suspense } from "react";
import StoreMenu from "./StoreMenu";

const StoreDetail = () => {
  return (
    <div>
      <ErrorBoundary fallback={<div>실패요</div>}>
        <Suspense fallback={<div>로딩중</div>}>
          <StoreMenu />
        </Suspense>
      </ErrorBoundary>
    </div>
  )
}

export default StoreDetail;