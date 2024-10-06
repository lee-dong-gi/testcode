package com.study.testcode.programmers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static com.study.testcode.common.constant.DateConstants.CONFIG_TEST;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@SpringBootTest
@TestPropertySource(locations = CONFIG_TEST)
public class RepeatBinaryConversionNonCheatingTest {
    @Autowired
    private RepeatBinaryConversionNonCheating repeatBinaryConversionNonCheating;

    @Test
    void repeatBinaryConversionNonCheatingTest() {
        // given
        String test1 = "110010101001";
        String test2 = "01110";
        String test3 = "1111111";

        int[] expectedResult1 = {3,8};
        int[] expectedResult2 = {3,3};
        int[] expectedResult3 = {4,1};

        // when
        int[] result1 = repeatBinaryConversionNonCheating.solution(test1);
        int[] result2 = repeatBinaryConversionNonCheating.solution(test2);
        int[] result3 = repeatBinaryConversionNonCheating.solution(test3);


        // then
        assertArrayEquals(expectedResult1, result1);
        assertArrayEquals(expectedResult2, result2);
        assertArrayEquals(expectedResult3, result3);

    }
}
