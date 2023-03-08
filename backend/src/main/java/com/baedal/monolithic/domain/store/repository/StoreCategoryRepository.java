package com.baedal.monolithic.domain.store.repository;

import com.baedal.monolithic.domain.store.entity.Store;
import com.baedal.monolithic.domain.store.entity.StoreCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreCategoryRepository extends JpaRepository<StoreCategory,Long> {
}
