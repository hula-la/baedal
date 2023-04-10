package com.baedal.monolithic.domain.account.repository;

import com.baedal.monolithic.domain.account.entity.Account;
import com.baedal.monolithic.domain.account.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
