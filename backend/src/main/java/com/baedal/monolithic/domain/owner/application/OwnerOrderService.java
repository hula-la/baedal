package com.baedal.monolithic.domain.owner.application;

import com.baedal.monolithic.domain.order.application.OrderService;
import com.baedal.monolithic.domain.owner.dto.OwnerOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class OwnerOrderService {

    private final OrderService orderService;

    @Transactional
    public void updateOrder(Long accountId, Long orderId, OwnerOrderDto.PutReq orderPutReq) {

        // saga 패턴으로 권한 확인 추가하기

        orderService.updateOrder(orderId, orderPutReq);

    }

}
