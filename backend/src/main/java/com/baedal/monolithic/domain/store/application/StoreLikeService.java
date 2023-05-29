package com.baedal.monolithic.domain.store.application;

import com.baedal.monolithic.domain.store.dto.StoreDto;
import com.baedal.monolithic.domain.store.entity.Store;
import com.baedal.monolithic.domain.store.entity.StoreLike;
import com.baedal.monolithic.domain.store.exception.StoreException;
import com.baedal.monolithic.domain.store.exception.StoreStatusCode;
import com.baedal.monolithic.domain.store.repository.StoreLikeRepository;
import com.baedal.monolithic.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreLikeService {

    private final StoreLikeRepository storeLikeRepository;
    private final StoreService storeService;
    private final StoreRepository storeRepository;

    @Transactional(readOnly = true)
    public Boolean checkLike(Long accountId, Long storeId) {
        return storeLikeRepository.existsByAccountIdAndStoreId(accountId, storeId);
    }

    @Transactional
    public Boolean toggleLike(Long accountId, Long storeId) {

        Optional<StoreLike> storeLikeOpt = storeLikeRepository.findByAccountIdAndStoreId(accountId, storeId);

        if (storeLikeOpt.isEmpty()) {
            likeStore(accountId,storeId);
        } else {
            unLikeStore(storeLikeOpt.get().getId(), storeId);
        }

        return storeLikeOpt.isEmpty();
    }

    private void updateLikeCntOfStore(Long storeId, int plus){

        Store store = storeRepository.findStoreToUpdateById(storeId)
                .orElseThrow(() -> new StoreException(StoreStatusCode.NO_STORE));

        store.updateHeartNum(plus);
    }

    private void likeStore(Long accountId, Long storeId) {

        storeLikeRepository.save(
                StoreLike.builder()
                        .accountId(accountId)
                        .storeId(storeId)
                        .build());

        updateLikeCntOfStore(storeId, 1);
    }

    private void unLikeStore(Long storeLikeId, Long storeId) {

        storeLikeRepository.deleteById(storeLikeId);

        updateLikeCntOfStore(storeId, -1);
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
