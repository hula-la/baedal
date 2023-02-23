import MenuInfo from "./MenuInfo";

export interface MenusProps {
    menuId: number;
    name: string;
    price: number;
    image: string;
    detail: string;
    status: string;
}

const MenuGroup = ({ menus }: {menus:MenusProps[]}) => {

  return (
    <div>
      {menus.map(item => {
        return (
          <MenuInfo key={item.menuId} menu={item} />
        )
      })}
    </div>
  )
}

export default MenuGroup;