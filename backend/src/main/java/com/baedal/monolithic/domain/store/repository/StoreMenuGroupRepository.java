package com.baedal.monolithic.domain.store.repository;

import com.baedal.monolithic.domain.store.entity.Store;
import com.baedal.monolithic.domain.store.entity.StoreMenuGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreMenuGroupRepository extends JpaRepository<StoreMenuGroup,Long> {

    List<StoreMenuGroup> findByStoreIdOrderByPriority(Long storeId);

}
