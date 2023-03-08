package com.baedal.monolithic.domain.store.repository;

import com.baedal.monolithic.domain.store.entity.StoreMenu;
import com.baedal.monolithic.domain.store.entity.StoreMenuGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreMenuRepository extends JpaRepository<StoreMenu,Long> {

    List<StoreMenu> findByGroupIdOrderByPriority(Long groupId);

}
