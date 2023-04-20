package com.baedal.monolithic.domain.store.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

public class StoreCategoryDto {

    @Getter
    @Builder
    @EqualsAndHashCode(of = {"id"})
    public static class Info implements Serializable {

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
