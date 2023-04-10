package com.baedal.storeService.application.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="account-service")
public interface UserServiceClient {

    @GetMapping("/micro-service/users/{accountId}/name")
    String getUserName(@PathVariable("accountId") Long accountId);
}
