import { useEffect, useCallback } from "react";
import { useNavigate } from "react-router-dom";
import { useCookies } from "react-cookie";

interface menuInfo {
  "id": number,
  "name": string,
  "menuSummary": string,
  "totalPrice": number,
  "count": number
}

// 메뉴 상세정보에서 가게 이름도 필요
const tmpMenu = {
  "menuId": 3,
  "name": "깐풍기",
  "price": 3,
  "image": "https://amazon.com",
  "detail": "맛있음",
  "status": "SOLD"
}

const MenuDetail = () => {

  const [cookies, setCookie] = useCookies()
  const navigate = useNavigate()

  const handleAddCookie = useCallback(
    () => {
      // 현재 장바구니 내용 받아오기
      const tmpBasket = cookies.basket
      
      let flag: number = 0
      tmpBasket.map((e:menuInfo) => {
        if(e.id === tmpMenu.menuId) {
          e.count += 1
          e.totalPrice += tmpMenu.price
          flag = 1
        }
      });
      if(flag === 0) {
        const addMenu: menuInfo = {
          id: tmpMenu.menuId,
          name: tmpMenu.name,
          menuSummary: tmpMenu.name,
          totalPrice: tmpMenu.price,
          count: 1
        }
        const basket = tmpBasket.concat([addMenu])
        setCookie('basket', basket, {path: '/'})
      } else{
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