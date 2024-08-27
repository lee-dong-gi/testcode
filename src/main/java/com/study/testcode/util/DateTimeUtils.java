package com.study.testcode.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.study.testcode.common.constant.DateConstants.UNDIVIDED_PATTERN;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateTimeUtils {
    public static String getCurrentTimeOfUndividedPattern() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(UNDIVIDED_PATTERN));
    }
}
