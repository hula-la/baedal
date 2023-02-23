import React from "react";

interface MenuCardProps {
  name: string;
}
const MenuLabel = ({ name }: MenuCardProps) => {
  return (
    <div>
      <div>{name}</div>
    </div>
  );
};

export default MenuLabel;
