package com.baedal.monolithic.domain.store.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(indexes = {
        @Index(name = "IX_store_menu_option_group_01",columnList = "menu_id")
})
public class StoreMenuOptionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer priority;

    @NotNull
    @Builder.Default
    private boolean isRadio = false;

    private Integer min;
    private Integer max;

    @OneToMany(mappedBy = "optionGroup", orphanRemoval = true)
    @OrderBy("priority ASC")
    private Set<StoreMenuOption> options;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private StoreMenu menu;

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

