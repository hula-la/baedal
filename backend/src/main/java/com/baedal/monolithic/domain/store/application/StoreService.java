package com.baedal.monolithic.domain.store.application;

import com.baedal.monolithic.domain.account.exception.AccountException;
import com.baedal.monolithic.domain.account.exception.AccountExceptionCode;
import com.baedal.monolithic.domain.account.repository.AccountRepository;
import com.baedal.monolithic.domain.store.dto.StoreDto;
import com.baedal.monolithic.domain.store.entity.Store;
import com.baedal.monolithic.domain.store.exception.StoreException;
import com.baedal.monolithic.domain.store.exception.StoreStatusCode;
import com.baedal.monolithic.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final AccountRepository accountRepository;
    private final StoreMapper storeMapper;

    @Transactional(readOnly = true)
    @Cacheable(key = "#storeReq", value = "stores")
    public List<StoreDto.SummarizedInfo> findAllStores(StoreDto.GetReq storeReq) {

        return storeRepository.findAllByAddressIdAndCategoryId(
                                storeReq.getAddressId(),
                                storeReq.getCategoryId(),
                                storeReq.getPageVO().getLastIdx(),
                                storeReq.getPageVO().getPageNum())
                .stream()
                .map(storeMapper::mapToSummarizedStoreDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public StoreDto.DetailedInfo findStoreDetail(Long storeId) {

        Store store = storeRepository.findDetailedStoreById(storeId)
                .orElseThrow(()->new StoreException(StoreStatusCode.NO_STORE));

        String ownerName = accountRepository.findById(store.getOwnerId())
                .orElseThrow(()-> new AccountException(AccountExceptionCode.NO_USER))
                .getName();

        // 지역별배달팁 추가
        return storeMapper.mapToDetailedStoreDto(store, ownerName);
    }

    @Transactional(readOnly = true)
    public StoreDto.SummarizedInfo findStoreIntro(Long storeId) {

        return storeMapper.mapToSummarizedStoreDto(
                storeRepository.findById(storeId)
                .orElseThrow(()->new StoreException(StoreStatusCode.NO_STORE))
        );
    }

    @Transactional(readOnly = true)
    @Cacheable(key = "#storeReq", value = "storeCnt")
    public Long countStores(StoreDto.GetReq storeReq) {

        return storeRepository.countAllByAddressIdAndCategoryId(
                storeReq.getAddressId(),
                storeReq.getCategoryId());
    }

    @Transactional
    public Long createStore(Long ownerId, StoreDto.PostPutReq storePostReq) {

        Store store = storeMapper.mapToEntity(ownerId, storePostReq);

        return storeRepository.save(store).getId();
    }

    @Transactional
    public void updateStore(Long storeId, StoreDto.PostPutReq storePostReq) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(()->new StoreException(StoreStatusCode.NO_STORE));

        store.update(storePostReq);
    }

    @Transactional
    public void deleteStore(Long storeId) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(()->new StoreException(StoreStatusCode.NO_STORE));

        // 접수된 주문이면 삭제 불가
        storeRepository.delete(store);
    }

}
