package com.baedal.monolithic.domain.store.dto;

import com.baedal.monolithic.domain.store.entity.StoreMenuStatus;
import lombok.*;

import java.io.Serializable;
import java.util.List;

public class MenuDto {
    @Getter
    @Builder
    public static class Menu implements Serializable{

        private static final long serialVersionUID = -342341228534127459L;

        private Long id;
        private String name;
        private Long price;
        private String img;
        private String expIntro;
        private String expDetail;
        private StoreMenuStatus status;
        private List<OptionGroup> optionGroup;

    }


    @Getter
    @Builder
    @ToString
    @EqualsAndHashCode(of = {"id"})
    public static class Group implements Serializable {

        private static final long serialVersionUID = -34234234234559L;


        private Long id;
        private String name;
        private String detail;
        private List<Menu> menus;

    }

    @Getter
    @Builder
    public static class OptionGroup implements Serializable {

        private static final long serialVersionUID = -234341234127459L;


        private Long id;
        private String name;
        private Boolean isRadio;
        private Integer min;
        private Integer max;
        private List<Option> options;

    }

    @Getter
    @Builder
    public static class Option implements Serializable {

        private static final long serialVersionUID = -5647123457459L;


        private Long id;
        private String name;
        private Long price;

    }



    @AllArgsConstructor
    @Getter
    public static class GetRes {
        private List<Group> menuGroups;
    }
}
