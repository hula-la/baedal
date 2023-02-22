interface ItemProps {
  id: number;
  name: string;
  menuSummary: string;
  totalPrice: number;
}

const BasketItem = ({ item }: { item: ItemProps }) => {
  return (
    <li>
      <div>
        <div>
          {item.name}
        </div>
        <div>
          {item.menuSummary}
        </div>
      </div>
    </li>
  )
}

export default BasketItem;