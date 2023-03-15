import { useQuery } from "@tanstack/react-query";
import { getAddress } from "../api/addressApi";

export const useGetAddress = () => {
  const data:any = useQuery({
    queryKey: ["address"],
    queryFn: () => getAddress(),
    suspense: true
  });
  return data
}