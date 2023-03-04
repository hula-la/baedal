import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom'
import StoreItem from './storeItem';
import { storeInfoProps } from './storeList';

const tmpStoreInfo = {
  "id": 1,
  "name": "가게이름",
  "minPrice": 10000,
  "rating": 3.5,
  "img":"https://amazon.com"
}

test('StoreItemTest', () => {
  render(<StoreItem tmpStoreInfo={tmpStoreInfo} />)
  const Images = screen.getAllByRole('img')
  const StoreName = screen.getByText('가게이름')
  const MinPrice = screen.getByText('10000')
  const Rating = screen.getByText('3.5')

  expect(Images.length).toBe(1)
  expect(StoreName).toBeInTheDocument()
  expect(MinPrice).toBeInTheDocument()
  expect(Rating).toBeInTheDocument()
})