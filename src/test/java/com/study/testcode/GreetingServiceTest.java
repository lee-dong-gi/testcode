package com.study.testcode;

import com.study.testcode.lotto.GreetingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class GreetingServiceTest {

    @InjectMocks
    private GreetingService greetingService;

    @Test
    void testGreet() {
        String result = greetingService.greet("World");
        assertEquals("Hello, World", result);
    }
}
