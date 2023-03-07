import React from "react";
import styled from "styled-components";

interface InputProps {
  type?: string;
  value?: string;
  onChange?: React.ChangeEventHandler<HTMLInputElement>;
}

const Wrapper = styled.div`
  input{
    width: 520px;
    height: 50px;
    border-radius: 25px;
    padding-left: 20px;
    padding-right: 100px;
  }
`

const SearchBar = ({ type, value, onChange }: InputProps) => {
  return (
    <Wrapper>
      <input
        type={type}
        value={value}
        placeholder="가게 이름을 입력해주세요."
        onChange={onChange}
      />
    </Wrapper>
  );
};

export default SearchBar;
