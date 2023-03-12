package com.baedal.storeService.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class testController {

    @GetMapping("/test")
    public String test(HttpServletRequest request) {
        String usreId = request.getHeader("X-Authorization-Id");

        return "hello~, "+usreId;
    }
}
