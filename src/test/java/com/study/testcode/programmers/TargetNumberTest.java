package com.study.testcode.programmers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static com.study.testcode.common.constant.DateConstants.CONFIG_TEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = CONFIG_TEST)
public class TargetNumberTest {
    @Autowired
    private TargetNumber targetNumber;

    @Test
    void targetNumberTest() {
        int[] numbers1 = {1, 1, 1, 1, 1};
        int target1 = 3;
        assertEquals(5, targetNumber.dfsRecursive(numbers1, target1, 0, 0));

        int[] numbers2 = {4, 1, 2, 1};
        int target2 = 4;
        assertEquals(2, targetNumber.dfsRecursive(numbers2, target2, 0, 0));
    }
}
