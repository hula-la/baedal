import React from "react";

interface MenuCardProps {
  name: string;
}
const MenuCard = ({ name }: MenuCardProps) => {
  return (
    <div>
      <p>메뉴카드</p>
    </div>
  );
};

export default MenuCard;
