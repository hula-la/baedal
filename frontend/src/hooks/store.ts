import { useQuery } from "@tanstack/react-query";
import { storeCategory } from "../api/store";

export const useStoreCategory = () => {
  const data = useQuery({
    queryKey: ["store", "category"],
    queryFn: () => storeCategory(),
    suspense: true,
  });
  return data;
};
