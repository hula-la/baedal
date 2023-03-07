import React from "react";
import { useStoreCategory } from "../../../hooks/store";
import styled from "styled-components";
import MenuCard from "./MenuCard";
import MenuLabel from "./MenuLabel";

interface Categories {
  results: number;
  categories: Category;
}

interface Category {
  id: number;
  name: string;
  en_name: string;
}

const Wrapper = styled.div`
  display: grid;
  justify-content: center;
  grid-template-columns: 25vw 25vw 25vw;
  grid-template-rows: auto;
  .Card {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    position: relative;
  }
`

const Menu = () => {
  // const data = useStoreCategory();
  const data = [
    { id: 1, name: "치킨", en_name: "chicken" },
    { id: 2, name: "피자", en_name: "pizza"},
    { id: 3, name: "중식", en_name: "chiness" },
    { id: 4, name: "한식", en_name: "korean" },
  ];

  return (
    <Wrapper>
      {data.map((category: Category) => {
        return (
          <div className="Card" key={category.id}>
            <MenuCard name={category.en_name} />
            <MenuLabel name={category.name} />
          </div>
        );
      })}
    </Wrapper>
  );
};

export default Menu;
