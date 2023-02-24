import { useCookies } from "react-cookie"
import { Link } from "react-router-dom"
import { useNavigate } from "react-router-dom"
import { useCallback } from "react"
import BasketItem from "./components/basketItem"
import EmptyBasket from "./components/emptyBasket"

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

const BasketPage = () => {
  const [cookies] = useCookies()
  const navigate = useNavigate()

  const goToBack = useCallback(() => {
    navigate(-1)
  }, [])

  if(cookies.basket && cookies.basket.length){
    return (
      <div>
        <ul>
          {cookies.basket.map((item:cookieItem, idx:number) => {
            return (
              <BasketItem key={idx} item={item} idx={idx} />
            )
          })}
        </ul>
        <div>
          <button onClick={goToBack}>더 담으러 가기</button>
        </div>
        <div>
          <Link to="/order">
            <button>주문하기</button>
          </Link>
        </div>
      </div>
    )
  } else {
    return (
      <div>
        <EmptyBasket />
        <div>
          <button onClick={goToBack}>더 담으러 가기</button>
        </div>
      </div>
    )
  }
}

export default BasketPage;