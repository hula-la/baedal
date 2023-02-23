package com.baedal.monolithic.domain.store.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public class MenuGroupFindAllDto {

    private Long id;
    private String name;
    private String detail;
    private List<MenuDto> menus;

}
