package com.baedal.accountservice.security.api;

import com.baedal.accountservice.security.application.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshToken(HttpServletRequest request, HttpServletResponse response,
                                       @RequestBody Map<String, String> accessTokenMap) {
        return ResponseEntity.ok().body(authService.refreshToken(request, response, accessTokenMap.get("accessToken")));
    }

}
