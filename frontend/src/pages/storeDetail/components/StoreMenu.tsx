import MenuGroup from "./MenuGroup";

const tmpMenu = [
  {
      "menuGroupId":1,
      "groupName": "국종류",
      "items": [
          {
              "menuId": 1,
              "name": "깐풍기",
              "price": 3,
              "image": "https://amazon.com",
              "detail": "맛있음",
              "status": "SOLD"
          }
      ]
  },
  {
      "menuGroupId":2,
      "groupName": "밥종류",
      "items": [
          {
              "menuId": 3,
              "name": "깐풍기",
              "price": 3,
              "image": "https://amazon.com",
              "detail": "맛있음",
              "status": "SOLD"
          },
          {
              "menuId": 4,
              "name": "깐풍기",
              "price": 3,
              "image": "https://amazon.com",
              "detail": "맛있음",
              "status": "SOLD"
          }
      ]
  }
]

const StoreMenu = () => {
  return (
    <div>
      {
        tmpMenu.map(item => {
          return (
            <div key={item.menuGroupId} >
              <div>
                {item.groupName}
              </div>
              <MenuGroup menus={item.items} />
            </div>
          )
        })
      }
    </div>
  )
}

export default StoreMenu;