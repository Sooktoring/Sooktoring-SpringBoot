package com.project.sooktoring.profile.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.sooktoring.profile.enumerate.AcademicStatus;
import com.project.sooktoring.profile.enumerate.Major;
import lombok.Getter;

import java.time.YearMonth;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Getter
public class AcademicInfoRequest {

    private String realName;

    private AcademicStatus academicStatus;

    @JsonFormat(pattern = "yyyy/MM", shape = STRING)
    private YearMonth entranceDate;

    @JsonFormat(pattern = "yyyy/MM", shape = STRING)
    private YearMonth graduationDate;

    private Boolean isGraduation;

    private Major mainMajor;

    private Major doubleMajor;

    private Major minorMajor;
}