package com.baedal.monolithic.domain.review.dto;

import com.baedal.monolithic.domain.store.dto.StoreMenuStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReviewDto {

    private Long id;
    private String nickName;
    private Long rating;
    private String content;
    private List<String> menus;

}
