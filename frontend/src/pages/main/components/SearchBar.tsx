import React from "react";

interface InputProps {
  type?: string;
  value?: string;
  onChange?: React.ChangeEventHandler<HTMLInputElement>;
}

const SearchBar = ({ type, value, onChange }: InputProps) => {
  return (
    <div>
      <input
        type={type}
        value={value}
        placeholder="검색어를 입력해주세요."
        onChange={onChange}
      />
    </div>
  );
};

export default SearchBar;
