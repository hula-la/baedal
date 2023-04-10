package com.baedal.monolithic.domain.review.dto;

import com.baedal.monolithic.domain.store.dto.PageVO;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ReviewDto {


    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Info {

        private Long id;
        private String nickName;
        private int rating;
        private String content;
        private List<String> menus;

    }


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetRes {
//        private Long results; // 총 갯수
        private List<Info> reviews;
    }

    @Getter
    @Setter
    @ToString
    public static class GetReq {

        private PageVO pageVO = new PageVO();

    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostReq {
        @NotNull(message = "{notnull}")
        private Long orderId;
        @NotNull(message = "{notnull}")
        private Integer rating;
        @NotNull(message = "{notnull}")
        private String content;
    }
}
