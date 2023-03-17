import { useEffect } from "react";
import { useLocation } from "react-router-dom";
import { useRecoilState } from "recoil";
import { addressAtom } from "../../atoms/atoms";
import { getAddress } from "../../api/addressApi";
import { useNavigate } from "react-router-dom";

const RedirectPage = () => {

  const location = useLocation()
  const navigate = useNavigate()
  const [address, setAddress] = useRecoilState(addressAtom)

  const getToken = () => {
    if (location.search) {
      const token = location.search.split('=')[1]
      localStorage.setItem("accessToken", token);
    }
  }
  
  useEffect(() => {
    getToken();
    if (!address.length) {
      getAddress().then((data) => {
        if (!data.addresses.length) {
          navigate('/address')
        } else {
          setAddress(data.addresses)
        }
      })
    }
  }, [])

  return (
    <div></div>
  )
}

export default RedirectPage;