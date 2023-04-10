package com.baedal.storeService.dto;

import com.baedal.storeService.entity.StoreMenuStatus;
import lombok.*;

import java.util.List;

public class MenuDto {
    @Getter
    public static class SummarizedInfo {

        private Long id;
        private String name;
        private Long price;
        private String img;
        private String expIntro;
        private StoreMenuStatus status;

    }

    @Setter
    @Getter
    public static class DetailedInfo {

        private Long id;
        private String name;
        private Long price;
        private String img;
        private String expDetail;
        private List<OptionGroup> optionGroup;

    }

    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode(of = {"id"})
    public static class Group {

        private Long id;
        private String name;
        private String detail;
        private List<SummarizedInfo> menus;

    }

    @Getter
    public static class Option {

        private Long id;
        private String name;
        private Long price;

    }

    @Setter
    @Getter
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
