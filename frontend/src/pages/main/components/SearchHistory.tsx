import React from "react";

interface HistoryProps {
  keywords: Keywords[];
}

interface Keywords {
  id: string;
  text: string;
}

const SearchHistory = ({ keywords }: HistoryProps) => {
  if (keywords.length === 0) {
    return <div>최근 검색어가 없습니다.</div>;
  }
  return (
    <div>
      {keywords.map(({ id, text }) => {
        return (
          <div key={id}>
            <div>{text}</div>
          </div>
        );
      })}
    </div>
  );
};

export default React.memo(SearchHistory);
