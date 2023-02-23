package com.baedal.monolithic.domain.store.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class MenuDto {

    private Long id;
    private String name;
    private Long price;
    private String img;
    private String expIntro;
    private StoreMenuStatus status;

}
