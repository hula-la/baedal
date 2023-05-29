package com.baedal.monolithic.domain.auth.util;

import com.baedal.monolithic.domain.auth.application.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        String userId = userDetails.getUsername();

        jwtProvider.refreshRefreshToken(response, userId);

        String accessToken = jwtProvider.createAccessToken(userId);

        clearAuthenticationAttributes(request, response);

        String targetUrl = determineTargetUrl(request, accessToken);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);

    }

    private String determineTargetUrl(HttpServletRequest request, String accessToken) {
      
        String targetUrl = CookieUtil
                .getCookie(request, CookieAuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue)
                .orElse(getDefaultTargetUrl());

        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("accessToken", accessToken)
                .build().toUriString();
    }


    private void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        cookieAuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

}
