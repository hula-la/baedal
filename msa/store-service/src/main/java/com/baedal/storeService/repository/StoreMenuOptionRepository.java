package com.baedal.storeService.repository;

import com.baedal.storeService.entity.StoreMenuOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreMenuOptionRepository extends JpaRepository<StoreMenuOption,Long> {

    List<StoreMenuOption> findAllByGroupIdOrderByGroupId(Long groupId);

}
