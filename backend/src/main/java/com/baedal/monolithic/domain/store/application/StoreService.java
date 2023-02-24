package com.baedal.monolithic.domain.store.application;

import com.baedal.monolithic.domain.account.exception.AccountException;
import com.baedal.monolithic.domain.account.exception.AccountExceptionCode;
import com.baedal.monolithic.domain.account.repository.AccountRepository;
import com.baedal.monolithic.domain.store.api.StoreController;
import com.baedal.monolithic.domain.store.dto.StoreFindAllDto;
import com.baedal.monolithic.domain.store.dto.StoreFindDto;
import com.baedal.monolithic.domain.store.entity.DeliveryAddress;
import com.baedal.monolithic.domain.store.entity.Store;
import com.baedal.monolithic.domain.store.entity.StoreTipByPrice;
import com.baedal.monolithic.domain.store.exception.StoreException;
import com.baedal.monolithic.domain.store.exception.StoreStatusCode;
import com.baedal.monolithic.domain.store.repository.DeliveryAddressRepository;
import com.baedal.monolithic.domain.store.repository.StoreRepository;
import com.baedal.monolithic.domain.store.repository.StoreTipByPriceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final AccountRepository accountRepository;
    private final DeliveryAddressRepository deliveryAddressRepository;
    private final StoreTipByPriceRepository storeTipByPriceRepository;
    private final ModelMapper modelMapper;

    public List<StoreFindAllDto> findAllStores(StoreController.StoreReq storeReq) {

        return storeRepository.findAllByAddressIdAndCategoryId(
                                storeReq.getAddressId(),
                                storeReq.getCategoryId(),
                                storeReq.getPageVO().getLastIdx(),
                                storeReq.getPageVO().getPageNum())
                .stream()
                .map(store -> modelMapper.map(store, StoreFindAllDto.class))
                .collect(Collectors.toList());
    }

    public StoreFindDto findStoreDetail(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(()->new StoreException(StoreStatusCode.NO_STORE));


        StoreFindDto storeFindDto = modelMapper.map(store, StoreFindDto.class);

        String ownerName = accountRepository.findById(store.getOwnerId())
                .orElseThrow(()-> new AccountException(AccountExceptionCode.NO_USER))
                .getName();

        storeFindDto.setOwnerName(ownerName);
        // 지역별배달팁 추가
        return storeFindDto;
    }
    public StoreFindAllDto findStoreIntro(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(()->new StoreException(StoreStatusCode.NO_STORE));

        StoreFindAllDto storeFindDto = modelMapper.map(store, StoreFindAllDto.class);

        // 지역별배달팁 추가
        return storeFindDto;
    }

    public Long countStores(StoreController.StoreReq storeReq) {
        return storeRepository.countAllByAddressIdAndCategoryId(
                storeReq.getAddressId(),
                storeReq.getCategoryId());
    }

    public Long getTipByAddressId(Long storeId, Long addressId) {
        DeliveryAddress deliveryAddress = deliveryAddressRepository.findByStoreIdAndAddressId(storeId, addressId)
                .orElseThrow(() -> new StoreException(StoreStatusCode.NO_DELIVERY_REGION));
        return deliveryAddress.getTip();
    }

    public Long getTipByPrice(Long storeId, Long price) {
        List<StoreTipByPrice> deliveryAddress = storeTipByPriceRepository.findByStoreIdOrderByPriceDesc(storeId);
        for (StoreTipByPrice tipByPrice:deliveryAddress) {
            if (price>=tipByPrice.getPrice()) {
                return tipByPrice.getTip();
            }
        }
        throw new StoreException(StoreStatusCode.NOT_EXCEED_MIN_PRICE);
    }

}
