import { useEffect } from "react";
import { useLocation } from "react-router-dom";
import { useRecoilState } from "recoil";
import { addressAtom } from "../../atoms/atoms";
import { useGetAddress } from "../../hooks/address";

const RedirectPage = () => {

  const location = useLocation()

  const getToken = () => {
    if (location.search) {
      const token = location.search.split('=')[1]
      localStorage.setItem("accessToken", token);
    }
  }
  
  useEffect(() => {
    getToken();
  }, [])

  // const [address, setAddress] = useRecoilState(addressAtom)
  // const data = useGetAddress();

  // useEffect(() => {
  //   if (!address) {
  //     console.log(data)
  //   }
  // })

  return (
    <div></div>
  )
}

export default RedirectPage;