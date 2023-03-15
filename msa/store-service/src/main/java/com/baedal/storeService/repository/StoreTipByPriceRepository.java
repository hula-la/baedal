package com.baedal.storeService.repository;

import com.baedal.storeService.entity.StoreTipByPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreTipByPriceRepository extends JpaRepository<StoreTipByPrice,Long> {

    List<StoreTipByPrice> findByStoreIdOrderByPriceDesc(Long storeId);

}
