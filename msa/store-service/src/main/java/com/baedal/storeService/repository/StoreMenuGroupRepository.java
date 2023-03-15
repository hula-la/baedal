package com.baedal.storeService.repository;

import com.baedal.storeService.entity.StoreMenuGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreMenuGroupRepository extends JpaRepository<StoreMenuGroup,Long> {

    List<StoreMenuGroup> findByStoreIdOrderByPriority(Long storeId);

}
