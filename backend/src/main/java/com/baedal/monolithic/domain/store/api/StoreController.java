package com.baedal.monolithic.domain.store.api;

import com.baedal.monolithic.domain.store.application.StoreService;
import com.baedal.monolithic.domain.store.dto.StoreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public ResponseEntity<StoreDto.GetRes> findAll (@Valid @ModelAttribute StoreDto.GetReq storeReq) {

        return ResponseEntity.ok(
                new StoreDto.GetRes(
                        storeService.countStores(storeReq),
                        storeService.findAllStores(storeReq)
                )
        );
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<StoreDto.DetailedInfo> find (@PathVariable Long storeId) {

        return ResponseEntity.ok(storeService.findStoreDetail(storeId));
    }


}
