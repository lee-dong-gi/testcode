package com.study.testcode.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {
    GLOBAL_BAD_REQUEST("GB001", "잘못된 요청입니다.", "요청이 잘못된 경우");

    private final String code;
    private final String message;
    private final String description;
}
