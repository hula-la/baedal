import { MenusProps } from "./MenuGroup";

const MenuInfo = ({ menu } : {menu:MenusProps}) => {
  return (
    <div>
      {menu.name}
    </div>
  )
}

export default MenuInfo;