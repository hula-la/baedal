package com.baedal.monolithic.domain.store.repository;

import com.baedal.monolithic.domain.store.entity.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress,Long> {

    Optional<DeliveryAddress> findByStoreIdAndAddressId(Long storeId, Long addressId);
}
