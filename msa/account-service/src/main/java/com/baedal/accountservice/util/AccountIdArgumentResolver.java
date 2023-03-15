package com.baedal.accountservice.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Component
public class AccountIdArgumentResolver implements HandlerMethodArgumentResolver {

    @Value("${jwt.user-id.header}")
    private String userIdHeader;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Long.class) &&
                parameter.hasParameterAnnotation(AccountId.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
        String userId = request.getHeader(userIdHeader);

        if (userId != null) {
            return Long.parseLong(userId);
        } else throw new RuntimeException("접근제한이 없습니다");
    }
}
