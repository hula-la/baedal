package com.baedal.monolithic.domain.account.entity;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String sido;
    @NotNull
    private String sigungu;
    private String dong;
//    private String roadName;
//    private String name;

}
