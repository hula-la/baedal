package com.baedal.monolithic.domain.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class StoreLikeDto {


    @AllArgsConstructor
    @Getter
    public static class StoreLikeRes {

        private Boolean isLike;
    }

    @AllArgsConstructor
    @Getter
    public static class StoreLikeFindAllRes {

        private Long results;
        private List<StoreDto.SummarizedInfo> stores;
    }
}
