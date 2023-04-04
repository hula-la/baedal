package com.baedal.monolithic.domain.store.dto;

import com.baedal.monolithic.domain.store.entity.StoreMenuStatus;
import lombok.*;

import java.io.Serializable;
import java.util.List;

public class MenuDto {
    @Getter
    @Builder
    public static class SummarizedMenu implements Serializable{

        private Long id;
        private String name;
        private Long price;
        private String img;
        private String expIntro;
        private StoreMenuStatus status;

    }

    @Getter
    @Builder
    public static class DetailedMenu {

        private Long id;
        private String name;
        private Long price;
        private String img;
        private String expDetail;
        private List<OptionGroup> optionGroup;

    }

    @Getter
    @Builder
    @ToString
    @EqualsAndHashCode(of = {"id"})
    public static class Group implements Serializable {

        private Long id;
        private String name;
        private String detail;
        private List<SummarizedMenu> menus;

    }

    @Getter
    @Builder
    public static class Option {

        private Long id;
        private String name;
        private Long price;

    }

    @Getter
    @Builder
    public static class OptionGroup {

        private Long id;
        private String name;
        private Boolean isRadio;
        private Integer min;
        private Integer max;
        private List<Option> options;

    }

    @AllArgsConstructor
    @Getter
    public static class GetRes {
        private List<Group> menuGroups;
    }
}
