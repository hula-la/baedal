package com.baedal.monolithic.domain.store.entity;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Table(indexes = {
        @Index(name = "IX_store_menu_option_01",columnList = "groupId")
})
public class StoreMenuOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long groupId;

    @NotNull
    private String name;

    @NotNull
    private Long price;

    @NotNull
    private Integer priority;


}
