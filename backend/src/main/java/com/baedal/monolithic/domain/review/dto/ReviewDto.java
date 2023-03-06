package com.baedal.monolithic.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ReviewDto {


    @Getter
    @Setter
    public static class Info {

        private Long id;
        private String nickName;
        private Long rating;
        private String content;
        private List<String> menus;

    }

    @AllArgsConstructor
    @Getter
    public static class GetRes {
//        private Long results; // 총 갯수
        private List<Info> reviews;
    }

    @Setter
    @Getter
    public static class PostReq {
        @NotNull(message = "{notnull}")
        private Long orderId;
        @NotNull(message = "{notnull}")
        private Integer rating;
        @NotNull(message = "{notnull}")
        private String content;
    }
}
