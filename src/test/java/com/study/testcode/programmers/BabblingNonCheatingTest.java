package com.study.testcode.programmers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static com.study.testcode.common.constant.DateConstants.CONFIG_TEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = CONFIG_TEST)
public class BabblingNonCheatingTest {
    @Autowired
    private BabblingNonCheating babblingNonCheating;

    @Test
    void billFoldingTest() {
        String[] test1 = new String[]{"aya", "yee", "u", "maa", "wyeoo"};
        assertEquals(1, babblingNonCheating.solution(test1));

        String[] test2 = new String[]{"ayaye", "uuuma", "ye", "yemawoo", "ayaa"};
        assertEquals(3, babblingNonCheating.solution(test2));
    }
}
