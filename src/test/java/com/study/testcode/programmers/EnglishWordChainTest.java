package com.study.testcode.programmers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static com.study.testcode.common.constant.DateConstants.CONFIG_TEST;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@SpringBootTest
@TestPropertySource(locations = CONFIG_TEST)
public class EnglishWordChainTest {
    @Autowired
    private EnglishWordChain englishWordChain;

    @Test
    void englishWordChainTest() {
        String[] test1 = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        int[] result1 = englishWordChain.solution(3, test1);
        assertArrayEquals(new int[]{3,3}, result1);

        String[] test2 = {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
        int[] result2 = englishWordChain.solution(5, test2);
        assertArrayEquals(new int[]{0,0}, result2);
    }
}
