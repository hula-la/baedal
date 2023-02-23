import { useCallback } from "react";
import { useNavigate } from "react-router-dom";
import { useCookies } from "react-cookie";

// 메뉴 상세정보에서 가게 이름도 필요
const tmpMenu = {
  "id": 3,
  "name": "깐풍기",
  "price": 4,
  "image": "https://amazon.com",
  "detail": "맛있음",
  "status": "SOLD"
}

const MenuDetail = () => {

  const [cookies, setCookie] = useCookies()
  const navigate = useNavigate()

  const handleAddCookie = useCallback(
    () => {
      let tmpBasket = cookies.basket
      let key:string = String(tmpMenu.name)
      // 쿠키 값이 있다
      if (tmpBasket) {
        // 같은 키값이 있으면 count 늘려줌
        if (Object.keys(tmpBasket).includes(key)) {
          tmpBasket[key][0] += 1
          tmpBasket[key][1] += tmpMenu.price
          setCookie('basket', tmpBasket, {path: '/'})
        // 같은 키값이 없다면 하나 추가
        } else {
          tmpBasket[key] = [1, tmpMenu.price]
          setCookie('basket', tmpBasket, {path: '/'})
        }
      // 쿠키값이 없으면 오브젝트로 만들고 값 넣어줌
      } else {
        tmpBasket = {}
        tmpBasket[key] = [1, tmpMenu.price]
        setCookie('basket', tmpBasket, {path: '/'})
      }
    }, [])
    
  const goToStore = useCallback(() => {
    navigate(-1)
  }, [])
  
  const handleAddBasket = useCallback(() => {
    handleAddCookie()
    goToStore()
  }, [])
  
  return (
    <div>
      <div>
        메뉴이미지
      </div>
      <div>
        {tmpMenu.name}
      </div>
      <div>
        {tmpMenu.price}
      </div>
      <div>
        <button onClick={handleAddBasket}>담기</button>
      </div>
    </div>
  )
}

export default MenuDetail;