package com.project.sooktoring.profile.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.YearMonth;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Schema(description = "직무경력 등록, 수정 시 정보 전달하는 DTO")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class CareerRequest {

    @Schema(description = "직무경력 id", example = "1") //수정 시에는 required = true
    private Long careerId;

    @Schema(description = "직무명", example = "테스트 서버 벡엔드", required = true)
    @NotNull
    private String job;

    @Schema(description = "직급", example = "인턴", required = true)
    @NotNull
    private String position;

    @Schema(description = "직무 시작일", example = "2022/05", required = true)
    @NotNull
    @JsonFormat(pattern = "yyyy/MM", shape = STRING)
    private YearMonth startDate;

    @Schema(description = "직무 종료일", example = "2022/07")
    @JsonFormat(pattern = "yyyy/MM", shape = STRING)
    private YearMonth endDate;

    @Schema(description = "재직중 여부", example = "true")
    private Boolean isWork;
}
