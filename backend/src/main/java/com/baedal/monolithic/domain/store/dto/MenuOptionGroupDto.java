package com.baedal.monolithic.domain.store.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MenuOptionGroupDto {

    private Long id;
    private String name;
    private Boolean isRadio;
    private Integer min;
    private Integer max;
    private List<MenuOptionDto> options;

}
