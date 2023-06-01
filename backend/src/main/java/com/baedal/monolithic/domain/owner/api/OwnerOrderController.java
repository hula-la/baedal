package com.baedal.monolithic.domain.owner.api;

import com.baedal.monolithic.domain.owner.application.OwnerOrderService;
import com.baedal.monolithic.domain.owner.dto.OwnerOrderDto;
import com.baedal.monolithic.global.util.AccountId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owner/orders")
public class OwnerOrderController {

    private final OwnerOrderService ownerOrderService;


    @PutMapping("/{orderId}")
    public ResponseEntity<Void> update(@AccountId Long accountId,
                                       @PathVariable Long orderId,
                                       @Valid @RequestBody OwnerOrderDto.PutReq orderPutReq) {

        ownerOrderService.updateOrder(accountId, orderId, orderPutReq);
        return ResponseEntity.noContent().build();
    }

}
