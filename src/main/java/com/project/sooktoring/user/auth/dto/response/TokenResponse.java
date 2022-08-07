package com.project.sooktoring.user.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "재발급된 엑세스, 리프레시 토큰 반환하는 DTO")
public class TokenResponse {

    @Schema(description = "재발급된 엑세스 토큰")
    private String accessToken;

    @Schema(description = "재발급된 리프레시 토큰")
    private String refreshToken;

    @Schema(description = "만료된 엑세스 토큰으로 요청한 method")
    private String requestMethod;

    @Schema(description = "만료된 엑세스 토큰으로 요청한 uri")
    private String requestURI;

    @Schema(description = "만료된 엑세스 토큰으로 요청한 body")
    private Object requestBody;

    public void setToken(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
