package com.project.sooktoring.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "나에게 온 멘토링 감사카드 조회 시 반환하는 DTO")
public class MtrCardToResponse {

    @Schema(description = "멘토링 감사카드 id", example = "1")
    protected Long mtrCardId;

    @Schema(description = "멘티 id", example = "2")
    private Long menteeId;

    @Schema(description = "멘티 프로필 이미지 url")
    private String menteeImageUrl;

    @Schema(description = "감사카드 제목", example = "감사합니다.")
    private String title;

    @Schema(description = "감사카드 내용", example = "자소서 첨삭 감사드립니다.")
    private String content;
}
