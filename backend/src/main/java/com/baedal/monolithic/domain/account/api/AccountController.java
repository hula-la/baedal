package com.baedal.monolithic.domain.account.api;

import com.baedal.monolithic.domain.account.application.AccountService;
import com.baedal.monolithic.domain.account.dto.AccountDto;
import com.baedal.monolithic.global.util.AccountId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/my")
    public ResponseEntity<AccountDto.GetRes> find(@AccountId Long accountId) {
        return ResponseEntity.ok()
                .body(accountService.findAccount(accountId));
    }
    @PutMapping("/my")
    public ResponseEntity<Void> update(@AccountId Long accountId,
                                                    @RequestBody AccountDto.PutReq accountPutReq) {
        accountService.updateAccount(accountId, accountPutReq);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/my")
    public ResponseEntity<Void> delete(@AccountId Long accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }

}
