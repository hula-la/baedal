import { useState, useCallback } from "react"
import { useCookies } from "react-cookie"

const OrderPage = () => {
  const [cookies, removeCookie] = useCookies()
  // 주문 정보
  interface orderInfoType {
    disposableReq: boolean,
    kimchiReq: boolean,
    riderMsg: string,
    ownerMsg: string,
    menus: string
  }

  const [orderInfo, setOrderInfo] = useState<orderInfoType>({
    disposableReq: false,
    kimchiReq: false,
    riderMsg: '',
    ownerMsg: '',
    menus: ''
  })

  // 주문자 정보
  interface userInfoType {
    id: number,
    nickname: string,
    email: string,
    role: string,
    tel: string,
    profile: string
  }

  const userInfo:userInfoType = {
    id: 1,
    nickname: "훌랄라",
    email: "ks@naver.com",
    role: "User",
    tel:"010-1234-5678",
    profile:"https://www.amazon.com",
  }
  
  // 체크박스 핸들링
  const handleCheckedItem = useCallback(
    (checked: boolean, item: string) => {
      setOrderInfo((state) => {
        return {...state, [item]: checked}
      })
    }, []) 
  
  // 텍스트 핸들링
  const handleTextItem = useCallback(
    (text: string, item: string) => {
      setOrderInfo((state) => {
        return {...state, [item]: text}
      })
    }, [])

  return (
    <main>
      <div>
        <input type="checkbox" id='disposableReq' onChange={(e) => {
          handleCheckedItem(e.target.checked, e.target.id)
        }} />
      </div>
      <div>
        <input type="checkbox" id='kimchiReq' onChange={(e) => {
          handleCheckedItem(e.target.checked, e.target.id)
        }} />
      </div>
      <div>
        <input type="text" id="riderMsg" onChange={(e) => {
          handleTextItem(e.target.value, e.target.id)
        }} />
      </div>
      <div>
        <input type="text" id="ownerMsg" onChange={(e) => {
          handleTextItem(e.target.value, e.target.id)
        }} />
      </div>
      <div>
        가격 : {cookies.basket[0].totalPrice}
      </div>
    </main>
  )
};

export default OrderPage;