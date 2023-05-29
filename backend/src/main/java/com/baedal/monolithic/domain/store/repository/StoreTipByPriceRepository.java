package com.baedal.monolithic.domain.store.repository;

import com.baedal.monolithic.domain.store.entity.StoreTipByPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreTipByPriceRepository extends JpaRepository<StoreTipByPrice,Long> {

    List<StoreTipByPrice> findByStoreIdOrderByPriceDesc(Long storeId);

}
