package com.baedal.monolithic.domain.account.repository;

import com.baedal.monolithic.domain.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
