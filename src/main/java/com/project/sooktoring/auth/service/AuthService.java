package com.project.sooktoring.auth.service;

import com.project.sooktoring.auth.dto.request.TokenRequest;
import com.project.sooktoring.auth.dto.response.TokenResponse;
import com.project.sooktoring.auth.exception.ExpiredRefreshTokenException;
import com.project.sooktoring.auth.jwt.AuthToken;
import com.project.sooktoring.auth.jwt.AuthTokenProvider;
import com.project.sooktoring.auth.jwt.RefreshToken;
import com.project.sooktoring.auth.jwt.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthTokenProvider authTokenProvider;

    @Transactional
    public TokenResponse refresh(TokenRequest tokenRequest, HttpServletRequest request) {
        AuthToken accessToken = authTokenProvider.convertAuthToken(tokenRequest.getAccessToken());
        AuthToken refreshToken = authTokenProvider.convertAuthToken(tokenRequest.getRefreshToken());

        //Refresh Token 검증
        if(refreshToken.validateToken()) {
            //Access Token에서 providerId get
            String providerId = accessToken.getTokenClaims().getSubject();
            Long userId = accessToken.getTokenClaims().get("userId", Long.class);

            //DB에서 providerId 기반으로 Refresh Token 값 get
            RefreshToken dbRefreshToken = refreshTokenRepository.findByKey(userId)
                    .orElseThrow(() -> new ExpiredRefreshTokenException("Expired Refresh Token"));
            //Refresh Token 일치 검사
            if (!dbRefreshToken.getValue().equals(refreshToken.getToken())) {
                throw new ExpiredRefreshTokenException("Invalid Refresh Token");
            }

            //새로운 토큰 생성
            AuthToken newAccessToken = authTokenProvider.createAccessToken(providerId, userId);
            AuthToken newRefreshToken = authTokenProvider.createRefreshToken();
            //DB Refresh Token 업데이트
            dbRefreshToken.updateToken(newRefreshToken.getToken());

            //Access Token 만료 시, 요청 데이터 get
            HttpSession session = request.getSession(false); //세션 존재하지 않으면 null 반환
            //세션 존재
            if (session != null && request.isRequestedSessionIdValid()) {
                Object object = session.getAttribute("tokenResponse");
                session.invalidate(); //세션 종료

                if (object != null) {
                    TokenResponse tokenResponse = (TokenResponse) object;
                    tokenResponse.setToken(newAccessToken.getToken(), newRefreshToken.getToken());
                    return tokenResponse;
                }
            }

            return TokenResponse.builder()
                    .accessToken(newAccessToken.getToken())
                    .refreshToken(newRefreshToken.getToken())
                    .build();
        }

        return null;
    }
}
