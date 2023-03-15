package com.baedal.storeService.repository;

import com.baedal.storeService.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

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


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Store> findStoreToUpdateById(Long storeId);

}
