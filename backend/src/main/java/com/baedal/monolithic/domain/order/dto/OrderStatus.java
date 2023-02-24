package com.baedal.monolithic.domain.order.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public enum OrderStatus {

    WAIT,RECEIVED,DELIVERYING,COMPLETED;

}
