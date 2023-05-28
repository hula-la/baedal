package com.baedal.monolithic.domain.review.application;

import com.baedal.monolithic.domain.review.dto.ReviewDto;
import com.baedal.monolithic.domain.review.entity.Review;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class ReviewMapper {

    ReviewDto.Info mapEntityToGetDto(final String nickname,
                             final List<String> menus,
                             final Review review){
        return ReviewDto.Info.builder()
                .id(review.getId())
                .nickName(nickname)
                .rating(review.getRating())
                .content(review.getContent())
                .menus(menus)
                .build();
    }

    Review mapPostDtoToEntity(final Long accountId,
                              final Long storeId,
                              final ReviewDto.PostReq reviewPostReq){
        return Review.builder()
                .accountId(accountId)
                .storeId(storeId)
                .orderId(reviewPostReq.getOrderId())
                .rating(reviewPostReq.getRating())
                .content(reviewPostReq.getContent())
                .build();
    }

}
