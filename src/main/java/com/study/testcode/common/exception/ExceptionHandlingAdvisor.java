package com.study.testcode.common.exception;


import com.study.testcode.common.dto.CommonDataResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice(annotations = RestController.class)
public class ExceptionHandlingAdvisor {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonDataResponse<?> handleMissingServletRequestParameterException(BadRequestException ex) {
        return new CommonDataResponse<>(ExceptionCode.GLOBAL_BAD_REQUEST.getCode(), ex.getMessage(), null);
    }
}
