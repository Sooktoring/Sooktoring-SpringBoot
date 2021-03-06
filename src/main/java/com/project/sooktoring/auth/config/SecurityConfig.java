package com.project.sooktoring.auth.config;

import com.project.sooktoring.auth.exception.exhandler.AuthExceptionHandlerFilter;
import com.project.sooktoring.auth.jwt.JwtAuthenticationFilter;
import com.project.sooktoring.auth.jwt.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthTokenProvider tokenProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthExceptionHandlerFilter authExceptionHandlerFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/v3/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**", "/auth/**", "/", "/test").permitAll()
                .antMatchers("/img/*").permitAll() //이미지 업로드 테스트
                .antMatchers("/mentoring/to").hasRole("MENTOR")
//                .antMatchers("/mentee").hasRole("MENTEE")
                .anyRequest().authenticated().and()
                .headers()
                .frameOptions()
                .sameOrigin().and()
                .cors().and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(authExceptionHandlerFilter, JwtAuthenticationFilter.class);
    }
}
