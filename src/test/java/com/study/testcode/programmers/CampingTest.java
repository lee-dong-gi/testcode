package com.study.testcode.programmers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static com.study.testcode.common.constant.DateConstants.CONFIG_TEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = CONFIG_TEST)
public class CampingTest {
    @Autowired
    private Camping camping;

    @Test
    void campingTest() {
        int[][] test1 = {{0, 0}, {1, 1}, {0, 2}, {2, 0}};
        assertEquals(3, camping.solution(4, test1));
    }
}
