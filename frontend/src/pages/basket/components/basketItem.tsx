import { useCallback } from "react";
import { useCookies } from "react-cookie";

const BasketItem = ({ item }: { item: string }) => {

  const [cookies, setCookie] = useCookies()
  // 장바구니 쿠키에서 삭제
  const deleteCookie = useCallback(() => {
    const tmpBasket = cookies.basket
    delete tmpBasket[item]
    setCookie('basket', tmpBasket, {path: '/'})
  }, [])

  return (
    <li>
      <div>
        <div>
          {item}
        </div>
        <div>
          {cookies.basket[item][0]} 개
        </div>
        <div>
          {cookies.basket[item][1]} 원
        </div>
      </div>
      <div>
        <button onClick={deleteCookie}>삭제</button>
      </div>
    </li>
  )
}

export default BasketItem;