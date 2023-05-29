package com.baedal.monolithic.domain.auth.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try{
            filterChain.doFilter(request, response);
        }catch (TokenExpiredException e){
            //토큰의 유효기간 만료
            log.error("만료된 토큰입니다. {}", e.getMessage());
            setErrorResponse(response, AuthStatusCode.ACCESS_TOKEN_EXPIRED);
        }catch (JwtException | IllegalArgumentException | JWTVerificationException e){
            //유효하지 않은 토큰
            log.error("유효하지 않은 토큰입니다. {}", e.getMessage());
            setErrorResponse(response, AuthStatusCode.NO_VALID_TOKEN);
        }
    }
    private void setErrorResponse(
            HttpServletResponse response,
            AuthStatusCode errorCode
    ){

        response.setStatus(errorCode.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(), errorCode.getMessage());

        try{
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Getter
    @AllArgsConstructor
    public static class ErrorResponse{
        private final String code;
        private final String message;
    }
}