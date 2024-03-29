package com.project.sooktoring.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "재발급된 엑세스, 리프레시 토큰 반환하는 DTO")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {

    @Schema(description = "재발급된 엑세스 토큰")
    private String accessToken;

    @Schema(description = "재발급된 리프레시 토큰")
    private String refreshToken;
}
