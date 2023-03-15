package com.baedal.storeService.dto;

import lombok.*;

import java.util.List;

public class StoreCategoryDto {

    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode(of = {"id"})
    public static class Info {

        private Long id;
        private String name;

    }

    @AllArgsConstructor
    @Getter
    public static class GetRes {

        private Long results;
        private List<Info> categories;

    }
}
