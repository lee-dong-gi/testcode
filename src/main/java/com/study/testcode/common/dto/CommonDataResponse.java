package com.study.testcode.common.dto;

import com.study.testcode.util.DateTimeUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonDataResponse<T> {
    private String returnCode;
    private String returnMessage;
    private String datetime;
    private T data;

    public CommonDataResponse(String returnCode, String returnMessage, T data) {
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
        this.datetime = DateTimeUtils.getCurrentTimeOfUndividedPattern();
        this.data = data;
    }

    public CommonDataResponse(CommonResponseStatus responseStatus, T data) {
        this.returnCode = responseStatus.getCode();
        this.returnMessage = responseStatus.getMessage();
        this.datetime = DateTimeUtils.getCurrentTimeOfUndividedPattern();
        this.data = data;
    }

    public CommonDataResponse(CommonResponseStatus responseStatus) {
        this.returnCode = responseStatus.getCode();
        this.returnMessage = responseStatus.getMessage();
        this.datetime = DateTimeUtils.getCurrentTimeOfUndividedPattern();
    }
}
