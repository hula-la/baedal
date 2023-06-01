package com.baedal.monolithic.domain.auth.util;

import org.springframework.util.SerializationUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Optional;

public class CookieUtil {
    public static void addCookie(HttpServletResponse response,
                                 String cookieKey,
                                 String cookieValue,
                                 int maxAge) {

        Cookie cookie = new Cookie(cookieKey, cookieValue);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);

    }

    public static void deleteCookie(HttpServletResponse response,
                             String cookieKey) {

        Cookie cookie = new Cookie(cookieKey, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public static Optional<Cookie> getCookie(HttpServletRequest request,
                          String cookieKey) {

        Cookie[] cookies = request.getCookies();

        if (cookies==null) return Optional.empty();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieKey)) {
                return Optional.of(cookie);
            }
        }

        return Optional.empty();
    }

    public static String serialize(Object object) {
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(object));
    }

    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.cast(SerializationUtils.deserialize(
                Base64.getUrlDecoder()
                        .decode(cookie.getValue())));
    }
}
