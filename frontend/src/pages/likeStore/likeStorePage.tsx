import { ErrorBoundary } from "react-error-boundary";
import { Suspense } from "react";
import LikeStores from "./components/LikeStores";


const likeStorePage = () => {
  return (
    <div>
      <main>
        <ErrorBoundary fallback={<div>실패요</div>}>
          <Suspense fallback={<div>로딩중</div>}>
            <LikeStores />
          </Suspense>
        </ErrorBoundary>
      </main>
    </div>
  )
}

export default likeStorePage;