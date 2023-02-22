import React from "react";

interface ButtonProps {
  onClick?: React.MouseEventHandler<HTMLButtonElement>;
}
const SearchButton = ({ onClick }: ButtonProps) => {
  return (
    <div>
      <button onClick={onClick}>검색</button>
    </div>
  );
};

export default React.memo(SearchButton);
