package com.study.testcode.programmers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static com.study.testcode.common.constant.DateConstants.CONFIG_TEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = CONFIG_TEST)
public class OpenChatRoomNonCheatingTest {
    @Autowired
    private OpenChatRoomNonCheating openChatRoomNonCheating;
    @Autowired
    private OpenChatRoom openChatRoom;

    @Test
    void openChatRoomNonCheatingTest() {
        String[] record = new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
        String[] results = openChatRoomNonCheating.solution(record);
        for (String result : results) {
            System.out.println(result);
        }
    }

    @Test
    void openChatRoomTest() {
        String[] record = new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
        String[] results = openChatRoom.solution(record);
        for (String result : results) {
            System.out.println(result);
        }
    }
}
