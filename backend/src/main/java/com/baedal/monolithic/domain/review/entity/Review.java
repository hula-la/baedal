package com.baedal.monolithic.domain.review.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private int rating;
    @NotNull
    private String content;

}
