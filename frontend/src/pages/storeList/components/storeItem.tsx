import { storeInfoProps } from "./storeList";

const StoreItem = ({tmpStoreInfo}: {tmpStoreInfo:storeInfoProps}) => {

  return (
    <div>
      <img src={tmpStoreInfo.img} alt="" />
      <div>
        <div>
          {tmpStoreInfo.name}
        </div>
        <div>
          {tmpStoreInfo.minPrice}
        </div>
        <div>
          {tmpStoreInfo.rating}
        </div>
      </div>
    </div>
  )
}

export default StoreItem;