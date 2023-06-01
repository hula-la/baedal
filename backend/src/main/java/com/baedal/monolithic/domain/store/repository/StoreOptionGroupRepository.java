package com.baedal.monolithic.domain.store.repository;

import com.baedal.monolithic.domain.store.entity.StoreMenuOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreOptionGroupRepository extends JpaRepository<StoreMenuOptionGroup,Long> {


}
