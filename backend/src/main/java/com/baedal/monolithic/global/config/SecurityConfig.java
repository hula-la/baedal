package com.baedal.monolithic.global.config;

import com.baedal.monolithic.domain.auth.application.CustomOAuth2UserService;
import com.baedal.monolithic.domain.auth.exception.ExceptionHandlerFilter;
import com.baedal.monolithic.domain.auth.exception.JwtAccessDeniedHandler;
import com.baedal.monolithic.domain.auth.exception.JwtAuthenticationEntryPoint;
import com.baedal.monolithic.domain.auth.util.CookieAuthorizationRequestRepository;
import com.baedal.monolithic.domain.auth.util.JwtAuthenticationProcessingFilter;
import com.baedal.monolithic.domain.auth.util.OAuth2FailureHandler;
import com.baedal.monolithic.domain.auth.util.OAuth2LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2LoginSuccessHandler customAuthenticationSuccessHandler;
    private final OAuth2FailureHandler customAuthenticationFailureHandler;
    private final JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final ExceptionHandlerFilter exceptionHandlerFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().disable()
                .httpBasic().disable()
                .csrf().disable()
                .cors()
                .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                    .authorizationEndpoint()
                    .authorizationRequestRepository(cookieAuthorizationRequestRepository)
                .and()
                .redirectionEndpoint()
                .and()
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
                .and()
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler);

        http.exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)	// 401
                .accessDeniedHandler(jwtAccessDeniedHandler);

        http.addFilterBefore(jwtAuthenticationProcessingFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(exceptionHandlerFilter, JwtAuthenticationProcessingFilter.class);

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


//    public JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter() {
//        return new JwtAuthenticationProcessingFilter(jwtProvider, accountRepository);
//    }

}
