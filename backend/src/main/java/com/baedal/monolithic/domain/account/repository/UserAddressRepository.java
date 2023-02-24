package com.baedal.monolithic.domain.account.repository;

import com.baedal.monolithic.domain.account.entity.Account;
import com.baedal.monolithic.domain.account.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress,Long> {
}
