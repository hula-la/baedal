package com.baedal.monolithic.domain.store.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MenuDetailDto {

    private Long id;
    private String name;
    private Long price;
    private String img;
    private String expDetail;
    private List<MenuOptionGroupDto> optionGroup;

}
