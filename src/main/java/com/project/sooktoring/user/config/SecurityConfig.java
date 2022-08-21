package com.project.sooktoring.user.config;

import com.project.sooktoring.user.filter.AuthExceptionHandlerFilter;
import com.project.sooktoring.user.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthExceptionHandlerFilter authExceptionHandlerFilter;

    // org.springframework.security.web.firewall.RequestRejectedException: The request was rejected because the URL contained a potentially malicious String ";" 관련 오류
    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        firewall.setAllowSemicolon(true);
        return firewall;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/v3/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**", "/auth/**", "/", "/test").permitAll()
                .antMatchers("/**").permitAll() //테스트 위한
                .antMatchers("/img/*").permitAll() //이미지 업로드 테스트
                .antMatchers("/mentoring/to/**").hasRole("MENTOR")
                .antMatchers("/mentoring/card/to/**").hasRole("MENTOR")
                .anyRequest().authenticated().and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(authExceptionHandlerFilter, JwtAuthenticationFilter.class);

        return http.build();
    }
}