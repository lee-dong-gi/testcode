package com.study.testcode.common.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonResponseStatus {
    SUCCESS("000", "성공");
    private final String code;
    private final String message;

}
