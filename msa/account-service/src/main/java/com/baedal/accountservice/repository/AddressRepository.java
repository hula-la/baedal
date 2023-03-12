package com.baedal.accountservice.repository;

import com.baedal.accountservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("select a from Address a where concat(a.sido, ' ',a.sigungu, ' ',a.dong) like %:keyword%")
    List<Address> searchAddress(@Param("keyword") String keyword);
}
