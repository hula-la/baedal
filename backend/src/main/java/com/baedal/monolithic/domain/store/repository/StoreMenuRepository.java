package com.baedal.monolithic.domain.store.repository;

import com.baedal.monolithic.domain.store.entity.StoreMenu;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreMenuRepository extends JpaRepository<StoreMenu,Long> {

    @EntityGraph(attributePaths = {"optionGroups", "options"})
    Optional<StoreMenu> findMenuById(Long menuId);

}
