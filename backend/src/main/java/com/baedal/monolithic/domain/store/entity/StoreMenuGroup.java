package com.baedal.monolithic.domain.store.entity;

import com.baedal.monolithic.global.entity.BaseTime;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Table(indexes = {
        @Index(name = "IX_store_menu_group_01",columnList = "storeId")
})
public class StoreMenuGroup extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long storeId;
    @NotNull
    private String name;
    @NotNull
    private Integer priority;
    private String detail;


}
