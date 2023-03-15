package com.baedal.storeService.application;

import com.baedal.storeService.dto.StoreDto;
import com.baedal.storeService.entity.Store;
import com.baedal.storeService.entity.StoreLike;
import com.baedal.storeService.exception.StoreException;
import com.baedal.storeService.exception.StoreStatusCode;
import com.baedal.storeService.repository.StoreLikeRepository;
import com.baedal.storeService.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class StoreLikeService {

    private final StoreLikeRepository storeLikeRepository;
    private final StoreService storeService;
    private final StoreRepository storeRepository;

    @Transactional(readOnly = true)
    public Boolean checkLike(Long accountId, Long storeId) {
        return storeLikeRepository.existsByAccountIdAndStoreId(accountId, storeId);
    }

//    2023-03-14 12:10:43.755 - 2023-03-14 12:11:35.025
//    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
//    12:14:46.694 - 12:15:36.909
//    public synchronized Boolean toggleLike(Long accountId, Long storeId) {
//        Optional<StoreLike> storeLikeOpt = storeLikeRepository.findByAccountIdAndStoreId(accountId, storeId);
//
//        int plus = 0;
//
//        if (storeLikeOpt.isEmpty()) {
//            likeStore(accountId,storeId);
//            plus = 1;
//        }
//        else {
//            unLikeStore(storeLikeOpt.get().getId());
//            plus = -1;
//        }
//
//        // store의 like 수 업데이트
//        Store store = storeRepository.findById(storeId)
//                .orElseThrow(() -> new StoreException(StoreStatusCode.NO_STORE));
//        store.updateHeartNum(plus);
//
//        storeRepository.save(store);
//
//        return storeLikeOpt.isEmpty();
//    }

//    2023-03-14 12:19:45.991 - 12:20:06.977
    @Transactional
    public Boolean toggleLike(Long accountId, Long storeId) {
        Optional<StoreLike> storeLikeOpt = storeLikeRepository.findByAccountIdAndStoreId(accountId, storeId);

        int plus = 0;

        if (storeLikeOpt.isEmpty()) {
            likeStore(accountId,storeId);
            plus = 1;
        }
        else {
            unLikeStore(storeLikeOpt.get().getId());
            plus = -1;
        }

        // store의 like 수 업데이트
        Store store = storeRepository.findStoreToUpdateById(storeId)
                .orElseThrow(() -> new StoreException(StoreStatusCode.NO_STORE));
        store.updateHeartNum(plus);

        return storeLikeOpt.isEmpty();
    }

    public void likeStore(Long accountId, Long storeId) {
        storeLikeRepository.save(
                StoreLike.builder()
                        .accountId(accountId)
                        .storeId(storeId)
                        .build());
    }

    public void unLikeStore(Long storeLikeId) {
        storeLikeRepository.deleteById(storeLikeId);
    }

    @Transactional(readOnly = true)
    public List<StoreDto.SummarizedInfo> findAllLikeStores(Long accountId) {
        return storeLikeRepository.findByAccountId(accountId)
                .stream()
                .map(storeLike -> storeService.findStoreIntro(storeLike.getStoreId()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Long countLikes(Long accountId) {
        return storeLikeRepository.countByAccountId(accountId);
    }
}
