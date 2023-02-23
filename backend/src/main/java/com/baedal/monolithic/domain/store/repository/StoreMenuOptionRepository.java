package com.baedal.monolithic.domain.store.repository;

import com.baedal.monolithic.domain.store.entity.StoreMenuOption;
import com.baedal.monolithic.domain.store.entity.StoreMenuOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreMenuOptionRepository extends JpaRepository<StoreMenuOption,Long> {

    List<StoreMenuOption> findAllByGroupIdOrderByGroupId(Long groupId);

}
