package com.baedal.monolithic.domain.store.api;

import com.baedal.monolithic.domain.store.application.StoreLikeService;
import com.baedal.monolithic.domain.store.dto.StoreLikeDto;
import com.baedal.monolithic.global.util.AccountId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreLikeController {

    private final StoreLikeService likeService;

    @GetMapping("/{storeId}/likes")
    public ResponseEntity<StoreLikeDto.StoreLikeRes> checkLike(@AccountId Long accountId,
                                                               @PathVariable Long storeId) {

        return ResponseEntity.ok().body(new StoreLikeDto.StoreLikeRes(likeService.checkLike(accountId,storeId)));
    }
    @PostMapping("/{storeId}/likes")
    public ResponseEntity<StoreLikeDto.StoreLikeRes> toggleLike(@AccountId Long accountId,
                                                                @PathVariable Long storeId) {

        return ResponseEntity.ok().body(new StoreLikeDto.StoreLikeRes(likeService.toggleLike(accountId,storeId)));
    }


    @GetMapping("/likes")
    public ResponseEntity<StoreLikeDto.StoreLikeFindAllRes> findAllLikeStore(@AccountId Long accountId) {

        return ResponseEntity.ok().body(new StoreLikeDto.StoreLikeFindAllRes(
                likeService.countLikes(accountId),
                likeService.findAllLikeStores(accountId)
        ));
    }



}
