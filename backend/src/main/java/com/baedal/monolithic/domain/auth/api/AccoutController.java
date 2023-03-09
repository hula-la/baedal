package com.baedal.monolithic.domain.auth.api;

import com.baedal.monolithic.domain.auth.application.AuthService;
import com.baedal.monolithic.domain.auth.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AccoutController {

    private final AuthService authService;

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshToken(HttpServletRequest request, HttpServletResponse response,
                                       @RequestBody Map<String, String> accessTokenMap) {
        return ResponseEntity.ok().body(authService.refreshToken(request, response, accessTokenMap.get("accessToken")));
    }

}
