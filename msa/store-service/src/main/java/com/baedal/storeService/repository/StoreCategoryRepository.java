package com.baedal.storeService.repository;

import com.baedal.storeService.entity.StoreCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreCategoryRepository extends JpaRepository<StoreCategory,Long> {
}
