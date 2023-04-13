package com.baedal.monolithic.domain.store.entity;

import com.baedal.monolithic.global.entity.BaseTime;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Table(indexes = {
        @Index(name = "IX_store_menu_01",columnList = "groupId")
})
public class StoreMenu extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long groupId;
    @NotNull
    private String name;
    @NotNull
    private Integer priority;
    private String img;
    private Long price;
    private String expDetail;
    private String expIntro;
    @Enumerated(EnumType.STRING)
    private StoreMenuStatus status;


}
