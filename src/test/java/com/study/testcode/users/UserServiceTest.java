package com.study.testcode.users;

import com.study.testcode.users.dto.UsersRequestDto;
import com.study.testcode.users.dto.UsersResponseDto;
import com.study.testcode.users.entity.Users;
import com.study.testcode.users.repository.UsersRepository;
import com.study.testcode.users.service.UsersService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UsersService usersService;

    @Test
    void testGetUsersList() {
        // given
        UsersRequestDto.GetUsersRequest request = new UsersRequestDto.GetUsersRequest();
        List<Users> mockUsers = new ArrayList<>();
        // 필요한 만큼 Users 객체를 추가해서 테스트 데이터를 설정
        mockUsers.add(new Users(1L, "이동기", "oksk4753@gmail.com"));

        when(usersRepository.findAll()).thenReturn(mockUsers);

        // when
        List<UsersResponseDto.UsersResponse> results = usersService.getUsersList(request);

        // then
        results.forEach(result -> {
            logger.info("result :: {}", result.getName());
        });

        assertNotNull(results);
        assertEquals(mockUsers.size(), results.size());
        verify(usersRepository, times(1)).findAll();
    }

}
