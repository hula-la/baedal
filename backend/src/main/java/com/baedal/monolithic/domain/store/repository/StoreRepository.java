package com.baedal.monolithic.domain.store.repository;

import com.baedal.monolithic.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store,Long> {

    @Query(value = "select count(*) from store s " +
            "join delivery_address d on s.id = d.store_id " +
            "where d.address_id = :addressId and s.category_id = :categoryId" , nativeQuery = true)
    Long countAllByAddressIdAndCategoryId(Long addressId, Long categoryId);

    @Query(value = "select * from store s " +
            "join delivery_address d on s.id = d.store_id " +
            "where d.address_id = :addressId and s.category_id = :categoryId " +
            "and s.id>:lastIdx limit :pageNum" , nativeQuery = true)
    List<Store> findAllByAddressIdAndCategoryId(Long addressId, Long categoryId, Long lastIdx, Long pageNum);



}
