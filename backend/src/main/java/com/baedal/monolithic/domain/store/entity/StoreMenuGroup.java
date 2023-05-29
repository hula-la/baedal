package com.baedal.monolithic.domain.store.entity;

import com.baedal.monolithic.global.entity.BaseTime;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Getter
@Table(indexes = {
        @Index(name = "IX_store_menu_group_01",columnList = "store_id")
})
public class StoreMenuGroup extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer priority;

    private String detail;

    @OneToMany(mappedBy = "menuGroup")
    @OrderBy("priority ASC")
    private Set<StoreMenu> menus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Store store;

}
