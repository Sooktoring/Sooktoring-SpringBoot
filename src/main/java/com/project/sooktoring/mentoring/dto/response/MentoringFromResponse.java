package com.project.sooktoring.mentoring.dto.response;

import com.project.sooktoring.mentoring.enumerate.MentoringCat;
import com.project.sooktoring.mentoring.enumerate.MentoringState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "나의 멘토링 신청내역 조회 시 반환하는 DTO")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class MentoringFromResponse {

    @Schema(description = "멘토링 id", example = "1")
    protected Long mentoringId;

    @Schema(description = "신청한 멘토 프로필 id", example = "1")
    private Long mentorProfileId;

    @Schema(description = "신청한 멘토 닉네임", example = "개발자국")
    private String mentorNickName;

    @Schema(description = "신청한 멘토 직업", example = "백엔드 개발자")
    private String mentorJob;

    @Schema(description = "신청한 멘토 연차", example = "2")
    private Long mentorWorkYear;

    @Schema(description = "신청한 멘토 프로필 이미지 url")
    private String mentorProfileImageUrl;

    @Schema(description = "멘토링 카테고리", example = "자소서")
    protected MentoringCat cat;

    @Schema(description = "멘토링 신청 이유", example = "자소서 첨삭 부탁드립니다!")
    protected String reason;

    @Schema(description = "멘토에게 전하는 말 한마디", example = "감사합니다~")
    protected String talk;

    @Schema(description = "멘토링 상태", example = "ACCEPT")
    protected MentoringState state;
}
