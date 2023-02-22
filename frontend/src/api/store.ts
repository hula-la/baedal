import { client } from "./client";

export const storeCategory = async () => {
  const { data } = await client.get("/store/categories");
  return data;
};
