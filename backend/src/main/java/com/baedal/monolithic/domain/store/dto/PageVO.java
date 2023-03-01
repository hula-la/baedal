package com.baedal.monolithic.domain.store.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"lastIdx","pageNum","sort"})
public class PageVO {

    private Long lastIdx = -1L;

    private Long pageNum = 10L;

    private String sort;


}
