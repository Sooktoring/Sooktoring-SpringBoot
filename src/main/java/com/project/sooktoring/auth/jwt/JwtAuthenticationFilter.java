package com.project.sooktoring.auth.jwt;

import com.project.sooktoring.auth.jwt.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().startsWith("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authorizationHeader = request.getHeader("Authorization");
        //프론트에서 보낸 appToken(서버에서 발급한 accessToken) 검증
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String tokenStr = authorizationHeader.substring(7);
            AuthToken token = tokenProvider.convertAuthToken(tokenStr);

            if(token.validateToken()) {
                Authentication authentication = tokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication); //인증된 사용자 정보 Security Context에 저장
            }
        }

        filterChain.doFilter(request, response);
    }
}
