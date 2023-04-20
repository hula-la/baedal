package com.baedal.monolithic.domain.store.entity;

import com.baedal.monolithic.domain.order.dto.OrderDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Table(indexes = {
        @Index(name = "IX_store_menu_option_group_01",columnList = "menu_id")
})
public class StoreMenuOptionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private StoreMenu menuGroup;
    @NotNull
    private String name;
    @NotNull
    private Integer priority;
    @NotNull
    @Builder.Default
    private boolean isRadio = false;

    private Integer min;
    private Integer max;

    @OneToMany(mappedBy = "optionGroup")
    @OrderBy("priority ASC")
    private Set<StoreMenuOption> options;

    public long calculateTotalPrice(List<Long> checkedOptions) {
        Map<Long, Long> optionMap = options.stream()
                .collect(Collectors.toMap(StoreMenuOption::getId, StoreMenuOption::getPrice));

        return checkedOptions.stream()
                .map(optionMap::get)
                .reduce(0l, Long::sum);
    }

    public boolean isValidCntOfOptions(int cnt) {
        if (isRadio && cnt==1) return true;
        return !isRadio && min <= cnt && max >= cnt;
    }
}

