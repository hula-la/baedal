package com.baedal.monolithic.domain.store.repository;

import com.baedal.monolithic.domain.store.entity.Store;
import com.baedal.monolithic.domain.store.entity.StoreMenuOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreMenuOptionGroupRepository extends JpaRepository<StoreMenuOptionGroup,Long> {

    List<StoreMenuOptionGroup> findAllByMenuIdOrderByPriority(Long menuId);

}
