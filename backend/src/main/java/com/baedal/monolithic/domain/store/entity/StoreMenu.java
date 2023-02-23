package com.baedal.monolithic.domain.store.entity;

import com.baedal.monolithic.domain.store.dto.StoreMenuStatus;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Getter
public class StoreMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long groupId;
    @NotNull
    private String name;
    @NotNull
    private Integer priority;
    private String img;
    private String price;
    private String expDetail;
    private String expIntro;
    private StoreMenuStatus status;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

}
