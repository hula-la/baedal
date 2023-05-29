package com.baedal.monolithic.domain.owner.dto;

import com.baedal.monolithic.domain.order.entity.OrderStatus;
import lombok.Getter;
import lombok.Setter;

public class OwnerOrderDto {

    @Getter
    @Setter
    public static class PutReq {

        private OrderStatus orderStatus;
        private String cancelReason;
        private Integer durationMinutes;

    }

}
