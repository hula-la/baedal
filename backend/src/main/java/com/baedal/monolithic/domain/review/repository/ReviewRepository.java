package com.baedal.monolithic.domain.review.repository;

import com.baedal.monolithic.domain.review.entity.Review;
import com.baedal.monolithic.domain.store.entity.StoreMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findByStoreIdOrderByIdDesc(Long storeId);
    Optional<Review> findByOrderId(Long orderId);


}
