package com.project.sooktoring.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.YearMonth;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CareerResponse {

    @JsonProperty("careerId")
    private Long id;

    private String job;

    private String company;

    @JsonFormat(pattern = "yyyy/MM")
    private YearMonth startDate;

    @JsonFormat(pattern = "yyyy/MM")
    private YearMonth endDate;
}
