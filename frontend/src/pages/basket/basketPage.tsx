import BasketItem from "./components/basketItem"
import EmptyBasket from "./components/emptyBasket"
import { useCookies } from "react-cookie"
import { Link } from "react-router-dom"
import { useEffect } from "react"

const tmpOrderList = [
  {
    "id": 1,
    "name": "가게",
    "menuSummary": "마라탕 외 2개",
    "totalPrice": 23000,
  }
]

// const tmpOrderList:Array<any> = []

const BasketPage = () => {
  const [cookies, setCookie, removeCookie] = useCookies()
  
  useEffect(() => {
    setCookie('basket', tmpOrderList, {path: '/'})
  },[])

  if(cookies.basket.length){
    return (
      <div>
        <ul>
          {tmpOrderList.map(item => {
            return (
              <BasketItem key={item.id} item={item} />
            )
          })}
        </ul>
        <div>
          <Link to="./order">
            <button>주문하기</button>
          </Link>
        </div>
      </div>
    )
  } else {
    return (
      <EmptyBasket />
    )
  }
}

export default BasketPage;