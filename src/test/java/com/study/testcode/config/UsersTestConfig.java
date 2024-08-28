package com.study.testcode.config;

import com.study.testcode.users.entity.Users;
import com.study.testcode.users.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@TestConfiguration
public class UsersTestConfig {

    @Autowired
    private UsersRepository usersRepository;

    @Bean
    public MockMvc mockMvc(WebApplicationContext context) {
        return MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Bean
    public CommandLineRunner setUpUsers() {
        return args -> {
            Users user = Users.builder()
                    .name("이동기")
                    .email("oksk4753@gmail.com")
                    .build();

            usersRepository.save(user);
        };
    }
}
