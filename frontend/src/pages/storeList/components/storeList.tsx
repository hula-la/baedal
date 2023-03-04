import StoreItem from "./storeItem";

export interface storeInfoProps {
  'id': number,
  'name': string,
  'minPrice': number,
  'rating': number,
  'img': string
}

const StoreList = () => {

  const tmpStoreList:storeInfoProps[] = [
    {
      "id": 1,
      "name": "가게이름",
      "minPrice": 10000,
      "rating": 3.5,
      "img":"https://amazon.com"
    },
    {
      "id": 2,
      "name": "가게이름",
      "minPrice": 10000,
      "rating": 3.5,
      "img":"https://amazon.com"
    }
  ]

  return (
    <div>
      {
        tmpStoreList.map((info) => {
          return (
            <StoreItem tmpStoreInfo={info} />
          )
        })
      }
    </div>
  )
}

export default StoreList;