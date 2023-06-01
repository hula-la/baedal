package com.baedal.monolithic.domain.store.entity;

import com.baedal.monolithic.domain.store.dto.MenuPutPostDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(indexes = {
        @Index(name = "IX_store_menu_option_01",columnList = "group_id")
})
public class StoreMenuOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Long price;

    @NotNull
    private Integer priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private StoreMenuOptionGroup optionGroup;

    public void update(MenuPutPostDto.OptionReq menuGroupReq) {
        this.name = menuGroupReq.getName();
        this.price = menuGroupReq.getPrice();
        this.priority = menuGroupReq.getPriority();
    }
}
