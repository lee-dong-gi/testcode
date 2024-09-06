package com.study.testcode.programmers;

import com.study.testcode.users.UserServiceTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static com.study.testcode.common.constant.DateConstants.CONFIG_TEST;

@SpringBootTest
@TestPropertySource(locations = CONFIG_TEST)
public class MostReceivedGiftTest {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private MostReceivedGift mostReceivedGift;

    @Test
    void testMostReceivedGift() {
//        String[] friends = new String[]{"muzi", "ryan", "frodo", "neo"};
//        String[] friends = new String[]{"a", "b", "c"};
        String[] friends = new String[]{"joy", "brad", "alessandro", "conan", "david"};
//        String[] gifts = new String[]{"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};
//        String[] gifts = new String[]{"a b", "b a", "c a", "a c", "a c", "c a"};
        String[] gifts = new String[]{"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"};

        int result = mostReceivedGift.solution(friends, gifts);
        logger.info("result :: {}", result);
    }
}
