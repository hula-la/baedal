import React from "react";
import { useState, useCallback } from "react";

const AddressPage = () => {
  
  const [address, setAddress] = useState("");

  const handleAddress = useCallback(
    (e: React.ChangeEvent<HTMLInputElement>) => {
      setAddress(e.target.value)
    }, []
  )

  return (
    <div>
      <input type="text" value={address} onChange={handleAddress} />
    </div>
  )
}

export default AddressPage;