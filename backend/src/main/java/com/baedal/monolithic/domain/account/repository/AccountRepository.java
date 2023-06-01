package com.baedal.monolithic.domain.account.repository;

import com.baedal.monolithic.domain.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {

    Optional<Account> findBySocialId(String socialId);
}
