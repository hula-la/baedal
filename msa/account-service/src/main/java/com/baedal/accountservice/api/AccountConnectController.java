package com.baedal.accountservice.api;

import com.baedal.accountservice.application.AccountService;
import com.baedal.accountservice.dto.AccountDto;
import com.baedal.accountservice.util.AccountId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/micro-service/users")
@RequiredArgsConstructor
public class AccountConnectController {

    private final AccountService accountService;

    @GetMapping("/{accountId}/name")
    public ResponseEntity<String> findName(@PathVariable Long accountId) {
        return ResponseEntity.ok()
                .body(accountService.findAccountName(accountId));
    }
}
