import { client } from "./client";

export const getAddress = async () => {
  const { data } = await client.get("/users/my/address")
  return data
}