import { useCallback } from "react";
import { useCookies } from "react-cookie";

interface cookieItem {
  "storeId": number,
  "menuId": number,
  "name": string,
  "price": number,
  "count": number,
  "options": {
   [key: number] : number[]
  }
}

const BasketItem = ({ item, idx }: { item: cookieItem, idx:number }) => {

  const [cookies, setCookie] = useCookies()
  // 장바구니 쿠키에서 삭제
  const deleteCookie = useCallback(() => {
    const tmpBasket = cookies.basket
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
          {item.count} 개
        </div>
        <div>
          {item.price} 원
        </div>
      </div>
      <div>
        <button onClick={deleteCookie}>삭제</button>
      </div>
    </li>
  )
}

export default BasketItem;