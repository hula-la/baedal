package com.baedal.monolithic.domain.store.entity;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Table(indexes = {
        @Index(name = "IX_store_menu_option_group_01",columnList = "menuId")
})
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
