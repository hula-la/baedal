package com.baedal.accountservice.repository;

import com.baedal.accountservice.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

    List<UserAddress> findAllByAccountId(Long accountId);
}
