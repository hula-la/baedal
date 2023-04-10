package com.baedal.storeService.api;

import com.baedal.storeService.application.StoreLikeService;
import com.baedal.storeService.dto.StoreDto;
import com.baedal.storeService.util.AccountId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreLikeController {

    private final StoreLikeService likeService;

    @GetMapping("/{storeId}/likes")
    public ResponseEntity<StoreLikeRes> checkLike(@AccountId Long accountId, @PathVariable Long storeId) {
        return ResponseEntity.ok().body(new StoreLikeRes(likeService.checkLike(accountId,storeId)));
    }
    @PostMapping("/{storeId}/likes")
    public ResponseEntity<StoreLikeRes> toggleLike(@AccountId Long accountId, @PathVariable Long storeId) {
        return ResponseEntity.ok().body(new StoreLikeRes(likeService.toggleLike(accountId,storeId)));
    }


    @GetMapping("/likes")
    public ResponseEntity<StoreLikeFindAllRes> findAllLikeStore(@AccountId Long accountId) {
        return ResponseEntity.ok().body(new StoreLikeFindAllRes(
                likeService.countLikes(accountId),
                likeService.findAllLikeStores(accountId)
        ));
    }


    @AllArgsConstructor
    @Getter
    private static class StoreLikeRes {
        private Boolean isLike;
    }

    @AllArgsConstructor
    @Getter
    private static class StoreLikeFindAllRes {
        private Long results;
        private List<StoreDto.SummarizedInfo> stores;
    }


}
