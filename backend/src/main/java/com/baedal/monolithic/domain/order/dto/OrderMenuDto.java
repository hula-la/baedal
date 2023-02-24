package com.baedal.monolithic.domain.order.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class OrderMenuDto {

    private Long id;

    // 직접 추가
    private String menuName;

    private Integer cnt;
    private String options;

}
