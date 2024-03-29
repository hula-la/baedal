package com.baedal.monolithic.domain.store.entity;

import com.baedal.monolithic.domain.store.dto.MenuPutPostDto;
import com.baedal.monolithic.domain.store.exception.StoreException;
import com.baedal.monolithic.domain.store.exception.StoreStatusCode;
import com.baedal.monolithic.global.entity.BaseTime;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(indexes = {
        @Index(name = "IX_store_menu_01",columnList = "group_id")
})
public class StoreMenu extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer priority;

    @Enumerated(EnumType.STRING)
    private StoreMenuStatus status;

    private String img;
    private Long price;
    private String expDetail;
    private String expIntro;


    @OneToMany(mappedBy = "menu", orphanRemoval = true)
    @OrderBy("priority ASC")
    private Set<StoreMenuOptionGroup> optionGroups;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private StoreMenuGroup menuGroup;

    public long calculatePriceAndCheckValidation(Map<Long,List<Long>> options) {
        long totalPrice = 0;

        for (StoreMenuOptionGroup optionGroup:optionGroups){
            List<Long> option = options.getOrDefault(optionGroup.getId(), new ArrayList<>());

            if (!optionGroup.isValidCntOfOptions(option.size()))
                throw new StoreException(StoreStatusCode.NOT_MATCH_OPTION_CONDITION);

            totalPrice += optionGroup.calculateTotalPrice(option);
        }

        return totalPrice;
    }

    public void update(MenuPutPostDto.MenuReq menuReq) {
        this.name = menuReq.getName();
        this.img = menuReq.getImg();
        this.expIntro = menuReq.getExpIntro();
        this.expDetail = menuReq.getExpDetail();
        this.price = menuReq.getPrice();
        this.priority = menuReq.getPriority();
        this.status = menuReq.getStatus();
    }
}
