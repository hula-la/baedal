package com.baedal.monolithic.domain.review.repository;

import com.baedal.monolithic.domain.review.dto.ReviewDto;
import com.baedal.monolithic.domain.review.entity.Review;
import com.baedal.monolithic.domain.store.entity.StoreMenu;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {

//    @Query(value = "select * from store s " +
//            "join delivery_address d on s.id = d.store_id " +
//            "where d.address_id = :addressId and s.category_id = :categoryId " +
//            "and s.id>:lastIdx limit :pageNum" , nativeQuery = true)
    List<Review> findAllByStoreIdAndIdLessThanOrderByIdDesc(Long storeId, Long reviewId, Pageable pageable);

    Long countReviewByStoreId(Long storeId);

    Optional<Review> findByOrderId(Long orderId);


}
