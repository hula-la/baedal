package com.baedal.monolithic.domain.store.repository;

import com.baedal.monolithic.domain.store.entity.StoreLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreLikeRepository extends JpaRepository<StoreLike,Long> {

    Boolean existsByAccountIdAndStoreId(Long accountId, Long storeId);
    Optional<StoreLike> findByAccountIdAndStoreId(Long accountId, Long storeId);

    List<StoreLike> findByAccountId(Long accountId);
    Long countByAccountId(Long accountId);

}
