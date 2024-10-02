package com.study.testcode.programmers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static com.study.testcode.common.constant.DateConstants.CONFIG_TEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = CONFIG_TEST)
public class BillFoldingTest {
    @Autowired
    private BillFolding billFolding;

    @Test
    void billFoldingTest() {
        int[] wallet1 = {30, 15};
        int[] bill1 = {26, 17};
        assertEquals(1, billFolding.solution(wallet1, bill1));

        int[] wallet2 = {50, 50};
        int[] bill2 = {100, 241};
        assertEquals(4, billFolding.solution(wallet2, bill2));
    }
}
