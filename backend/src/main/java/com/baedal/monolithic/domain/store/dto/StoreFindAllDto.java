package com.baedal.monolithic.domain.store.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public class StoreFindAllDto {

    private Long id;
    private String name;
    private float rating = 0;
    private String img;
    
}
