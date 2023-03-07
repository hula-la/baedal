import React from "react";
import styled from "styled-components";

interface MenuCardProps {
  name: string;
}

const CardImage = styled.div<{ name: string }>`
  width: 100%;
  height: 11.5vw;
  margin: 1vw;
  background-image: url("img/main/${props => props.name}.jpg");
  background-size: cover;
`

const MenuCard = ({ name }: MenuCardProps) => {
  return (
    <CardImage name={name} />
  );
};

export default MenuCard;
