package com.baedal.monolithic.domain.account.repository;

import com.baedal.monolithic.domain.account.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAddressRepository extends JpaRepository<UserAddress,Long> {

    List<UserAddress> findAllByAccountId(Long accountId);
}
