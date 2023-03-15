import { useQuery } from "@tanstack/react-query";
import { storeCategory } from "../api/store";

export const useStoreCategory = () => {
  const data:any = useQuery({
    queryKey: ["store", "category"],
    queryFn: () => storeCategory(),
    suspense: true,
  });
  console.log(data)
  return data;
};
