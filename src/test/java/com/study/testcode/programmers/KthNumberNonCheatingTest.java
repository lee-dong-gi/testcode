package com.study.testcode.programmers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static com.study.testcode.common.constant.DateConstants.CONFIG_TEST;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = CONFIG_TEST)
public class KthNumberNonCheatingTest {
    @Autowired
    private KthNumberNonCheating kthNumberNonCheating;
    @Autowired
    private KthNumber kthNumber;
    @Autowired
    private KthNumberImplQuickSort kthNumberImplQuickSort;

    @Test
    void kthNumberTest() {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        int[] expected = {5, 6, 3};
        assertArrayEquals(expected, kthNumberNonCheating.solution(array, commands));
        assertArrayEquals(expected, kthNumber.solution(array, commands));
        assertArrayEquals(expected, kthNumberImplQuickSort.solution(array, commands));
    }
}
