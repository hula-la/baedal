package com.baedal.monolithic.domain.store.dto;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class PageVO {

    private Long lastIdx = -1L;

    @Value("${store.pageNum}")
    private Long pageNum;

    private String sort;

}
