package com.baedal.monolithic.domain.store.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class PageVO {

    private Long lastIdx = -1L;

    private Long pageNum = 10L;

    private String sort;

}
