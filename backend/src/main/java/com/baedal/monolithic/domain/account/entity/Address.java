package com.baedal.monolithic.domain.account.entity;

import lombok.Getter;
import lombok.Setter;

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

    public String getAddressName() {
        return sido + " " + sigungu + " " + dong;
    }

}
