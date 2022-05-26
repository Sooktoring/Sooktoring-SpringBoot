package com.project.sooktoring.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MentoringCat {

    INTRODUCTION("자소서"), PERSONALITY("인적성"), INTERVIEW("면접"), PORTFOLIO("포트폴리오");

    private final String value;

    MentoringCat(String value) {
        this.value = value;
    }

    //역직렬화
    @JsonCreator
    public static MentoringCat from(String value) {
        for (MentoringCat cat : MentoringCat.values()) {
            if (cat.getValue().equals(value)) {
                return cat;
            }
        }
        return null;
    }

    //직렬화
    @JsonValue
    public String getValue() {
        return value;
    }
}
