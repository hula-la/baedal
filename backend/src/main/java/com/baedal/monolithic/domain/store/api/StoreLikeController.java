package com.baedal.monolithic.domain.store.api;

import com.baedal.monolithic.domain.store.application.StoreLikeService;
import com.baedal.monolithic.domain.store.dto.StoreDto;
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
    public ResponseEntity<StoreLikeRes> checkLike(@PathVariable Long storeId) {
        Long accountId = 1L;
        return ResponseEntity.ok().body(new StoreLikeRes(likeService.checkLike(accountId,storeId)));
    }
    @PostMapping("/{storeId}/likes")
    public ResponseEntity<StoreLikeRes> toggleLike(@PathVariable Long storeId) {
        Long accountId = 1L;
        return ResponseEntity.ok().body(new StoreLikeRes(likeService.toggleLike(accountId,storeId)));
    }


    @GetMapping("/likes")
    public ResponseEntity<StoreLikeFindAllRes> findAllLikeStore() {
        Long accountId = 1L;
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
