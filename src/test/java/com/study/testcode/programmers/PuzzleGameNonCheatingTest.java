package com.study.testcode.programmers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static com.study.testcode.common.constant.DateConstants.CONFIG_TEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = CONFIG_TEST)
public class PuzzleGameNonCheatingTest {
    @Autowired
    private PuzzleGameNonCheating puzzleGameNonCheating;

    @Test
    void puzzleGameNonCheatingTest1() {
        int[] diffs = new int[]{1,5,3};
        int[] times = new int[]{2,4,7};
        long limit = 30;
        assertEquals(3, puzzleGameNonCheating.solution(diffs, times, limit));
    }

    @Test
    void puzzleGameNonCheatingTest2() {
        int[] diffs = new int[]{1,4,4,2};
        int[] times = new int[]{6,3,8,2};
        long limit = 59;
        assertEquals(2, puzzleGameNonCheating.solution(diffs, times, limit));
    }

    @Test
    void puzzleGameNonCheatingTest3() {
        int[] diffs = new int[]{1, 99999, 100000, 99995};
        int[] times = new int[]{9999, 9001, 9999, 9001};
        long limit = 3456789012L;
        assertEquals(39354, puzzleGameNonCheating.solution(diffs, times, limit));
    }
}
