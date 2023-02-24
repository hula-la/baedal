package com.baedal.monolithic.domain.store.repository;

import com.baedal.monolithic.domain.store.entity.DeliveryAddress;
import com.baedal.monolithic.domain.store.entity.Store;
import com.baedal.monolithic.domain.store.entity.StoreTipByPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StoreTipByPriceRepository extends JpaRepository<StoreTipByPrice,Long> {

    List<StoreTipByPrice> findByStoreIdOrderByPriceDesc(Long storeId);

}
