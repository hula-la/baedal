package com.baedal.monolithic.domain.store.repository;

import com.baedal.monolithic.domain.account.repository.AccountRepository;
import com.baedal.monolithic.domain.store.entity.Store;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("prod")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StoreRepositoryTest {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private NoFKStoreRepository nostoreRepository;
    @Autowired
    private NoFKAccountRepository noaccountRepository;


    private Long accountId;
    private static Long noaccountId;


//    @BeforeEach
//    void beforeAll() {
//        Account account = Account.builder().build();
//        accountRepository.save(account);
//        Long b = account.getId();
//
//        NoAccount noaccount = NoAccount.builder().build();
//        noaccountRepository.save(noaccount);
//        Long s= noaccount.getId();
//
//        for (int i = 0; i < 5; i++) {
//            Optional<NoAccount> aaccount = noaccountRepository.findById(s);
//
//            StoreNoFK store = StoreNoFK.builder().representativeId(aaccount.get()).build();
//            StoreNoFK save = nostoreRepository.save(store);
//            noaccountId = save.getId();
//        }
//
//        for (int i = 0; i < 5; i++) {
//            Optional<Account> vaccount = accountRepository.findById(b);
//
//            Store store = Store.builder().owner(vaccount.get()).build();
//            Store save = storeRepository.save(store);
//            accountId = save.getId();
//        }
//    }

    // 1m 43s
    @Test
    @Transactional
    @DisplayName("Compare")
    void compare() {
        Optional<Store> byId = storeRepository.findById(1L);

        Optional<StoreNoFK> byIsd = nostoreRepository.findById(1L);

        System.out.println(byId.get().toString());
        System.out.println(byIsd.get().toString());

    }

    // 1m 30s
//    @Test
//    @Transactional
//    @DisplayName("비교하기")
//    void compareNoFK() {
//        Optional<Store> byId = storeRepository.findById(3L);
//
//        Optional<StoreNoFK> byIsd = nostoreRepository.findById(3L);
//
//        System.out.println(byId.get().toString());
//        System.out.println(byIsd.get().toString());
//    }
}