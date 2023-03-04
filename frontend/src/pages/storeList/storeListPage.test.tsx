import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom'
import StoreListPage from './storeListPage';

test('storeListTest', () => {
  render(<StoreListPage />)
  const initialScreen = screen.getByText('업체 리스트')
  expect(initialScreen).toBeInTheDocument()
})