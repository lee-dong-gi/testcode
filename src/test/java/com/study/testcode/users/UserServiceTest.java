package com.study.testcode.users;

import com.study.testcode.config.UsersTestConfig;
import com.study.testcode.users.entity.Users;
import com.study.testcode.users.repository.UsersRepository;
import com.study.testcode.users.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static com.study.testcode.common.constant.DateConstants.CONFIG_TEST;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestPropertySource(locations = CONFIG_TEST)
@Import(UsersTestConfig.class)
public class UserServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private UsersRepository usersRepository;

    @Test
    void testGetUsers() {
        // given
        //when
//        Optional<Users> result = usersRepository.findByEmail("oksk4753@gmail.com");
        List<Users> result = usersRepository.findAll();

        // then
        assertThat(!result.isEmpty()).isTrue();
    }
}
