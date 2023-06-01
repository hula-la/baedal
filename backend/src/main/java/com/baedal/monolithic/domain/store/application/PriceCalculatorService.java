package com.baedal.monolithic.domain.store.application;

import com.baedal.monolithic.domain.order.dto.OrderDto;
import com.baedal.monolithic.domain.store.entity.DeliveryAddress;
import com.baedal.monolithic.domain.store.entity.StoreTipByPrice;
import com.baedal.monolithic.domain.store.exception.StoreException;
import com.baedal.monolithic.domain.store.exception.StoreStatusCode;
import com.baedal.monolithic.domain.store.repository.DeliveryAddressRepository;
import com.baedal.monolithic.domain.store.repository.StoreTipByPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PriceCalculatorService {

    private final MenuService menuService;
    private final DeliveryAddressRepository deliveryAddressRepository;
    private final StoreTipByPriceRepository storeTipByPriceRepository;

    @Transactional(readOnly = true)
    public Long calculateOrderPrice(List<OrderDto.MenuPostReq> menuPostReqs) {

        return menuPostReqs.stream()
                .map(menuService::calculatePriceOfMenu)
                .reduce(0L, Long::sum);
    }

    @Transactional(readOnly = true)
    public Long calculateTip(Long storeId, Long orderPrice, Long addressId) {

        return getTipByAddressId(storeId,addressId)
                + getTipByPrice(storeId,orderPrice);

    }

    private Long getTipByAddressId(Long storeId, Long addressId) {

        DeliveryAddress deliveryAddress = deliveryAddressRepository.findByStoreIdAndAddressId(storeId, addressId)
                .orElseThrow(() -> new StoreException(StoreStatusCode.NO_DELIVERY_REGION));

        return deliveryAddress.getTip();
    }

    private Long getTipByPrice(Long storeId, Long price) {

        List<StoreTipByPrice> deliveryAddress = storeTipByPriceRepository.findByStoreIdOrderByPriceDesc(storeId);

        for (StoreTipByPrice tipByPrice:deliveryAddress) {
            if (price>=tipByPrice.getPrice()) {
                return tipByPrice.getTip();
            }
        }

        throw new StoreException(StoreStatusCode.NOT_EXCEED_MIN_PRICE);
    }

}
