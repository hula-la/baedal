import { useCallback } from "react";
import { useNavigate } from "react-router-dom";
import { useCookies } from "react-cookie";
import _ from 'lodash'

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
// 메뉴 상세정보에서 가게 이름도 필요
const tmpMenu = {
  "id": 3,
  "name": "깐풍기",
  "price": 4,
  "image": "https://amazon.com",
  "detail": "맛있음",
  "status": "SOLD",
  "options": {2:[1, 4]}
}

const MenuDetail = () => {

  const [cookies, setCookie] = useCookies()
  const navigate = useNavigate()

  const handleAddCookie = useCallback(
    () => {
      // 쿠키 불러오기
      let tmpBasket = cookies.basket
      // 쿠키가 있는지 없는지 확인 없으면 빈 배열 줌
      if (!tmpBasket) {
        tmpBasket = []
      }
      // 같은게 있는지 알기 위한 flag
      let flag: number = 0
      // 메뉴 같은지 -> 옵션 같은지 비교
      tmpBasket.map((e:cookieItem) => {
        if(e.menuId === tmpMenu.id) {
          if (_.isEqual(e.options, tmpMenu.options)){
            e.count += 1
            e.price += tmpMenu.price
            flag = 1
          }
      }});
      // 같은 메뉴, 옵션이 없다면 추가
      if(flag === 0) {
        const addMenu: cookieItem = {
          storeId: 1,
          menuId: tmpMenu.id,
          name: tmpMenu.name,
          price: tmpMenu.price,
          count: 1,
          options: tmpMenu.options
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