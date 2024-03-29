package com.project.sooktoring.mentoring.dto.response;

import com.project.sooktoring.mentoring.enumerate.MentoringCat;
import com.project.sooktoring.mentoring.enumerate.MentoringState;
import com.project.sooktoring.profile.enumerate.Major;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "나에게 요청된 멘토링 신청내역 조회 시 반환하는 DTO")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class MentoringToResponse {

    @Schema(description = "멘토링 id", example = "1")
    protected Long mentoringId;

    @Schema(description = "신청한 멘티 프로필 id", example = "2")
    private Long menteeProfileId;

    @Schema(description = "신청한 멘티 실명", example = "김영희")
    private String menteeRealName;

    @Schema(description = "신청한 멘티 주전공명")
    private Major menteeMainMajor;

    @Schema(description = "신청한 멘티 실물 이미지 url")
    private String menteeIdImageUrl;

    @Schema(description = "멘토링 카테고리", example = "자소서")
    protected MentoringCat cat;

    @Schema(description = "멘토링 신청 이유", example = "자소서 첨삭 부탁드립니다!")
    protected String reason;

    @Schema(description = "멘토에게 전하는 말 한마디", example = "감사합니다~")
    protected String talk;

    @Schema(description = "멘토링 상태", example = "END")
    protected MentoringState state;
}
