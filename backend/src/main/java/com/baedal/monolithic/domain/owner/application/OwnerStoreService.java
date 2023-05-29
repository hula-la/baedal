package com.baedal.monolithic.domain.owner.application;

import com.baedal.monolithic.domain.store.application.StoreService;
import com.baedal.monolithic.domain.store.dto.StoreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class OwnerStoreService {

    private final StoreService storeService;


    @Transactional
    public Long createStore(Long ownerId, StoreDto.PostPutReq storePostPutReq) {

        return storeService.createStore(ownerId, storePostPutReq);
    }

    @Transactional
    public void updateStore(Long accountId, Long storeId, StoreDto.PostPutReq storePostPutReq) {

        storeService.updateStore(storeId, storePostPutReq);
    }

    @Transactional
    public void deleteStore(Long reviewId) {

        storeService.deleteStore(reviewId);
    }
}
