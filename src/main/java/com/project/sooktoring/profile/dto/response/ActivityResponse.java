package com.project.sooktoring.profile.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.YearMonth;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Schema(description = "대외활동 조회 시 반환하는 DTO")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResponse {

    @Schema(description = "대외활동 id", example = "1")
    private Long activityId;

    @Schema(description = "대외활동명", example = "SOLUX 교내 동아리")
    private String title;

    @Schema(description = "대외활동 설명", example = "백엔드 파트")
    private String details;

    @Schema(description = "대외활동 시작일", example = "2022/05")
    @JsonFormat(pattern = "yyyy/MM", shape = STRING)
    private YearMonth startDate;

    @Schema(description = "대외활동 종료일", example = "2022/07")
    @JsonFormat(pattern = "yyyy/MM", shape = STRING)
    private YearMonth endDate;

    @Schema(description = "활동중 여부", example = "true")
    private Boolean isActive;
}
