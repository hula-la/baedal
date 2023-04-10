package com.baedal.monolithic.domain.review.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(indexes = {
        @Index(name = "IX_store_01",columnList = "accountId,storeId")
})
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long accountId;
    @NotNull
    private Long storeId;

    @NotNull
    private Long orderId;
    @NotNull
    private Integer rating;
    @NotNull
    private String content;

}
