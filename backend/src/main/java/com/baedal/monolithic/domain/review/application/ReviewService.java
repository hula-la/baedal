package com.baedal.monolithic.domain.review.application;

import com.baedal.monolithic.domain.account.application.AccountService;
import com.baedal.monolithic.domain.order.application.OrderService;
import com.baedal.monolithic.domain.review.dto.ReviewDto;
import com.baedal.monolithic.domain.review.entity.Review;
import com.baedal.monolithic.domain.review.exception.ReviewException;
import com.baedal.monolithic.domain.review.exception.ReviewStatusCode;
import com.baedal.monolithic.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final AccountService accountService;
    private final OrderService orderService;
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public List<ReviewDto.Info> findReviews(Long storeId, ReviewDto.GetReq reviewGetReq) {

        Long lastIdx = reviewGetReq.getPageVO().getLastIdx()==-1L?
                Integer.MAX_VALUE:reviewGetReq.getPageVO().getLastIdx();

        List<Review> reviewEntitys = reviewRepository.findAllByStoreIdAndIdLessThanOrderByIdDesc(storeId,
                lastIdx,
                PageRequest.of(0, Math.toIntExact(reviewGetReq.getPageVO().getPageNum()))
        );

        return reviewEntitys.stream()
                .map(review ->
                        reviewMapper.mapEntityToGetDto(
                        accountService.getUserNickname(review.getAccountId()),
                        orderService.getMenuNames(review.getOrderId()),
                        review)
                ).collect(Collectors.toList());
    }

    @Transactional
    public Long createReview(Long accountId, Long storeId, ReviewDto.PostReq reviewPostReq) {

        Review review = reviewMapper.mapPostDtoToEntity(accountId, storeId, reviewPostReq);

        return reviewRepository.save(review).getId();
    }

    @Transactional
    public void updateReview(Long accountId, Long reviewId, ReviewDto.PostReq reviewPostReq) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewException(ReviewStatusCode.NO_ORDER));

        if (!review.getAccountId().equals(accountId)) throw new ReviewException(ReviewStatusCode.NO_ACCESS);

        review.update(reviewPostReq.getRating(), reviewPostReq.getContent());
    }

    @Transactional
    public void deleteReview(Long reviewId) {

        reviewRepository.delete(reviewRepository.findById(reviewId)
                .orElseThrow(()->new ReviewException(ReviewStatusCode.NO_ORDER)));
    }
}
