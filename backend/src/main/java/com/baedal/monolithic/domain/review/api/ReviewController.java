package com.baedal.monolithic.domain.review.api;

import com.baedal.monolithic.domain.review.application.ReviewService;
import com.baedal.monolithic.domain.review.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/stores/{storeId}")
    public ResponseEntity<ReviewDto.GetRes> findAll(@PathVariable Long storeId) {
        return ResponseEntity.ok()
                .body(new ReviewDto.GetRes(reviewService.findReviews(storeId)));
    }

    @PostMapping("/stores/{storeId}")
    public ResponseEntity<Void> create(@PathVariable Long storeId, @Valid @RequestBody ReviewDto.PostReq reviewPostReq) {
        Long accountId = 1L;
        Long reviewId = reviewService.createReview(accountId,storeId, reviewPostReq);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(reviewId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Void> update(@PathVariable Long reviewId, @Valid @RequestBody ReviewDto.PostReq reviewPostReq) {
        Long accountId = 1L;
        reviewService.updateReview(accountId, reviewId, reviewPostReq);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> delete(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }

}
