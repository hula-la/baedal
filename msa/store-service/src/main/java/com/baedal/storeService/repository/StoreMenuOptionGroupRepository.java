package com.baedal.storeService.repository;

import com.baedal.storeService.entity.StoreMenuOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreMenuOptionGroupRepository extends JpaRepository<StoreMenuOptionGroup,Long> {

    List<StoreMenuOptionGroup> findAllByMenuIdOrderByPriority(Long menuId);

}
