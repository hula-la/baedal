import { MenusProps } from "./MenuGroup";
import { useNavigate } from "react-router-dom";

const MenuInfo = ({ menu } : {menu:MenusProps}) => {

  const navigate = useNavigate()

  return (
    <div>
      {menu.name}
      <button onClick={() => navigate(`./${menu.menuId}`)}>클릭</button>
    </div>
  )
}

export default MenuInfo;