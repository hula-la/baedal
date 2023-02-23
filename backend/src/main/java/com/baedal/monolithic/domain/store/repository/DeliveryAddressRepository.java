package com.baedal.monolithic.domain.store.repository;

import com.baedal.monolithic.domain.store.entity.DeliveryAddress;
import com.baedal.monolithic.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress,Long> {
}
