import { useCallback } from "react";
import { useCookies } from "react-cookie";

interface ItemProps {
  id: number;
  name: string;
  menuSummary: string;
  totalPrice: number;
  count: number;
}

const BasketItem = ({ item }: { item: ItemProps }) => {

  const [cookies, setCookie] = useCookies()

  const deleteCookie = useCallback(() => {
    const tmpBasket = cookies.basket
    let idx = -1
    for (let index = 0; index < tmpBasket.length; index++) {
      if(tmpBasket[index].id === item.id) {
        idx = index
        break
      }
    }
    console.log(idx)
    tmpBasket.splice(idx, 1)
    setCookie('basket', tmpBasket, {path: '/'})
  }, [])

  return (
    <li>
      <div>
        <div>
          {item.name}
        </div>
        <div>
          {item.menuSummary}
        </div>
        <div>
          {item.totalPrice}
        </div>
        <div>
          {item.count}
        </div>
      </div>
      <div>
        <button onClick={deleteCookie}>삭제</button>
      </div>
    </li>
  )
}

export default BasketItem;