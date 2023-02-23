import React from "react";
import { useStoreCategory } from "../../../hooks/store";
import MenuCard from "./MenuCard";
import MenuLabel from "./MenuLabel";

interface Categories {
  results: number;
  categories: Category;
}

interface Category {
  id: number;
  name: string;
}

const Menu = () => {
  // const data = useStoreCategory();
  const data = [
    { id: 1, name: "치킨" },
    { id: 2, name: "피자" },
    { id: 3, name: "중식" },
  ];

  return (
    <div>
      {data.map((category: Category) => {
        return (
          <div key={category.id}>
            <MenuCard name={category.name} />
            <MenuLabel name={category.name} />
          </div>
        );
      })}
    </div>
  );
};

export default Menu;
