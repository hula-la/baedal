import { Navigate, Outlet } from "react-router-dom";

export const CheckLogin = () => {
  const accessToken = localStorage.getItem('accessToken')
  if (!accessToken) {
    return <Navigate to={'/login'} replace />
  }
  return <Outlet />
}