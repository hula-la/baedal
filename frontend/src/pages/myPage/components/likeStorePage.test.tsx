import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom'
import LikeStorePage from './likeStorePage';

test('테스트', () => {
  render(<LikeStorePage />);
  const linkElement = screen.getByText(/찜/i);
  expect(linkElement).toBeInTheDocument();
})