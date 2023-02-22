import React, { Suspense, useEffect } from "react";
import { ErrorBoundary } from "react-error-boundary";
import { useStoreCategory } from "../../../hooks/store";

interface Categories {
  results: number;
  categories: Category;
}

interface Category {
  id: number;
  name: string;
}

const Menu = () => {
  const data = useStoreCategory();

  return (
    <div>
      <p>{data.data}</p>
    </div>
  );
};

export default Menu;
