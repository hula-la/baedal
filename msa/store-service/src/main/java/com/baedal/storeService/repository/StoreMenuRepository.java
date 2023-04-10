package com.baedal.storeService.repository;

import com.baedal.storeService.entity.StoreMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreMenuRepository extends JpaRepository<StoreMenu,Long> {

    List<StoreMenu> findByGroupIdOrderByPriority(Long groupId);

}
