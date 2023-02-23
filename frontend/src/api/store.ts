import { StoreParam } from "../models/store";
import { client } from "./client";

export const storeCategory = async () => {
  const { data } = await client.get("/store/categories");
  return data;
};

export const storeList = async (params: StoreParam) => {
  const { data } = await client.get("/stores", { params });
  return data;
};
