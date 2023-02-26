package com.baedal.monolithic.domain.order.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class OrderFindIntroDto {

    private Long id;

    private String name; // 추가해줘야함
    private String menuSummary;
    private Long totalPrice;

    @NotNull(message = "{notnull}")
    private Timestamp orderAt;
    private Timestamp exArrivalTime;

}
