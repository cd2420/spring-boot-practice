package com.example.dmaker.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DMakerErrorCode {
    DUPLICATED_MEMBER_ID("중복된 아이디."),
    LEVEL_EXPERIENCE_YEAR_NOT_MATCH("개발자 레벨과 연차가 맞지 않습니다.");

    private final String message;
}
