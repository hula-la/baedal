import { useCookies } from "react-cookie"
import { Link } from "react-router-dom"
import { useNavigate } from "react-router-dom"
import { useCallback } from "react"
import BasketItem from "./components/basketItem"
import EmptyBasket from "./components/emptyBasket"

const BasketPage = () => {
  const [cookies] = useCookies()
  const navigate = useNavigate()

  const goToBack = useCallback(() => {
    navigate(-1)
  }, [])

  if(cookies.basket && Object.keys(cookies.basket).length){
    return (
      <div>
        <ul>
          {Object.keys(cookies.basket).map((key:string) => {
            return (
              <BasketItem key={key} item={key} />
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