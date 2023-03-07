import React from "react";
import styled from "styled-components";

interface ButtonProps {
  onClick?: React.MouseEventHandler<HTMLButtonElement>;
}

const Wrapper = styled.div`
  position: absolute;
  right: -10px;
  top: 3px;
  button {
    width: 60px;
    height: 50px;
    border: 0;
    border-radius: 20px;
    background-color: white;
    cursor: pointer;
  }
`

const SearchButton = ({ onClick }: ButtonProps) => {
  return (
    <Wrapper>
      <button onClick={onClick}>검색</button>
    </Wrapper>
  );
};

export default React.memo(SearchButton);
