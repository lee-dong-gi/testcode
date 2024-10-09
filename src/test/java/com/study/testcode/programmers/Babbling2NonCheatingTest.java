package com.study.testcode.programmers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static com.study.testcode.common.constant.DateConstants.CONFIG_TEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = CONFIG_TEST)
public class Babbling2NonCheatingTest {
    @Autowired
    private Babbling2NonCheating babbling2NonCheating;

    @Test
    void babbling2Test() {
        String[] test1 = new String[]{"aya", "yee", "u", "maa"};
        assertEquals(1, babbling2NonCheating.solution(test1));

        String[] test2 = new String[]{"ayaye", "uuu", "yeye", "yemawoo", "ayaayaa"};
        assertEquals(2, babbling2NonCheating.solution(test2));
    }
}
