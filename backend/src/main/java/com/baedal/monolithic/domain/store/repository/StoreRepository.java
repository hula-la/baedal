package com.baedal.monolithic.domain.store.repository;

import com.baedal.monolithic.domain.store.entity.Store;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store,Long> {

    @Query(value = "select count(*) from store s " +
            "join delivery_address d on s.id = d.store_id " +
            "where d.address_id = :addressId and s.category_id = :categoryId" , nativeQuery = true)
    Long countAllByAddressIdAndCategoryId(@Param("addressId") Long addressId,
                                          @Param("categoryId") Long categoryId);

    @Query(value = "select * from store s " +
            "join delivery_address d on s.id = d.store_id " +
            "where d.address_id = :addressId and s.category_id = :categoryId " +
            "and s.id>:lastIdx limit :pageNum" , nativeQuery = true)
    List<Store> findAllByAddressIdAndCategoryId(@Param("addressId") Long addressId,
                                                @Param("categoryId") Long categoryId,
                                                @Param("lastIdx") Long lastIdx,
                                                @Param("pageNum") Long pageNum);

//    @EntityGraph(attributePaths = {"menuGroups", "menus", "optionGroups", "options"})
    @EntityGraph(attributePaths = {"menuGroups", "menuGroups.menus", "menuGroups.menus.optionGroups", "menuGroups.menus.optionGroups.options"})
    Optional<Store> findDetailedStoreById(Long storeId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Store> findStoreToUpdateById(Long storeId);

}
