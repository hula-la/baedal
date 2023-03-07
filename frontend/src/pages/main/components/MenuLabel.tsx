import React from "react";
import styled from "styled-components";

interface MenuCardProps {
  name: string;
}

const Label = styled.div`
  position: absolute;
  color: white;
`
const MenuLabel = ({ name }: MenuCardProps) => {


  return (
    <Label>
      {name}
    </Label>
  );
};

export default MenuLabel;
