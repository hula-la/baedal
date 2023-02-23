package com.baedal.monolithic.domain.store.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
public class StoreMenuOptionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long menuId;
    @NotNull
    private String name;
    @NotNull
    private Integer priority;
    @NotNull
    private boolean isRadio = false;

    private Integer min;
    private Integer max;

}
