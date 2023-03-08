package com.baedal.monolithic.domain.store.application;

import com.baedal.monolithic.domain.store.dto.StoreDto;
import com.baedal.monolithic.domain.store.entity.StoreLike;
import com.baedal.monolithic.domain.store.repository.StoreLikeRepository;
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

    @Transactional(readOnly = true)
    public Boolean checkLike(Long accountId, Long storeId) {
        return storeLikeRepository.existsByAccountIdAndStoreId(accountId, storeId);
    }

    public Boolean toggleLike(Long accountId, Long storeId) {
        Optional<StoreLike> storeLikeOpt = storeLikeRepository.findByAccountIdAndStoreId(accountId, storeId);

        if (storeLikeOpt.isEmpty()) likeStore(accountId,storeId);
        else unLikeStore(storeLikeOpt.get().getId());

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
