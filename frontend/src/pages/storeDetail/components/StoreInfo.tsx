import { useState } from "react";

const StoreInfo = () => {
  const [isLiked, setIsLiked] = useState(false)
  const handleLikeButton = () => {
    console.log('post요청')
    setIsLiked(!isLiked)
  }

  return (
    <div>
      가게 정보
      <div onClick={handleLikeButton}>
        {isLiked?
          <button>찜 해제</button>
        :
          <button>찜</button>
        }
      </div>
    </div>
  )
}

export default StoreInfo;