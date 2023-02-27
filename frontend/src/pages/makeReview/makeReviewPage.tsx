import { useState } from "react";
import { ReactComponent as Star } from './star.svg';

const MakeReviewPage = () => {

  const [rating, setRating] = useState(0);
  const [hoveredStarIndex, setHoveredStarIndex] = useState(0);

  const fillStar = (num: number, event?: string): string => {
    if (event === 'enter' && hoveredStarIndex >= num) {
      return '#ff7f23';
    }
    if (event === 'leave' && rating >= num) {
      return '#ff7f23';
    }
    return '#eeeeee';
  };

  return (
    <div>
      <header>리뷰쓰기</header>
      <main>
        <div>
          {[1, 2, 3, 4, 5].map((num) => (
            <button
              key={num}
              onMouseEnter={() => setHoveredStarIndex(num)}
              onMouseLeave={() => setHoveredStarIndex(0)}
              onClick={() => {
                setRating(num)
              }}
            >
              <Star key={num} fill={fillStar(num, hoveredStarIndex === 0 ? 'leave' : 'enter')} width={20} height={20} />
            </button>
          ))}
        </div>
        <div>
          <input type="text" name="" id="" />
        </div>
      </main>
    </div>
  )
}

export default MakeReviewPage;
